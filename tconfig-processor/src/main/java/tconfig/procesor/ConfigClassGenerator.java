package tconfig.procesor;

import com.squareup.javapoet.*;
import tconfig.annotation.TConfigProperty;
import tconfig.procesor.method.MethodImplementationFactory;
import tconfig.procesor.method.MethodImplementationGenerator;
import tconfig.spi.Configuration;

import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import java.util.Objects;

import static tconfig.procesor.method.MethodImplementationGenerator.DEFAULT_CONFIG_PARAMETER_NAME;

class ConfigClassGenerator {
    private static final String SUFFIX = "$tconfig";
    private final String configParameterName;

    private final Logger logger;
    private final TypeElement classElement;
    private final Elements elementUtils;
    private final MethodImplementationFactory methodImplementationFactory;

    ConfigClassGenerator(Logger logger,
                         TypeElement classElement,
                         Elements elementUtils,
                         MethodImplementationFactory methodImplementationFactory) {
        Objects.requireNonNull(logger);
        Objects.requireNonNull(classElement);
        Objects.requireNonNull(elementUtils);
        this.configParameterName = DEFAULT_CONFIG_PARAMETER_NAME;
        this.methodImplementationFactory = methodImplementationFactory;
        this.logger = logger;
        this.classElement = classElement;
        this.elementUtils = elementUtils;
    }

    public String getQualifiedName() {
        return classElement.getQualifiedName().toString();
    }

    private String getSimpleClassName() {
        return classElement.getSimpleName() + SUFFIX;
    }

    String getPackageName() {
        PackageElement packageElement = elementUtils.getPackageOf(classElement);
        return packageElement.isUnnamed() ? "" : packageElement.getQualifiedName().toString();
    }

    TypeSpec generateConfigClass() {
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(getSimpleClassName())
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ClassName.get(classElement));
        for (Element element : classElement.getEnclosedElements()) {
            if (ElementKind.METHOD.equals(element.getKind())) {
                ExecutableElement method = (ExecutableElement) element;
                TConfigProperty annotation = element.getAnnotation(TConfigProperty.class);
                classBuilder.addMethod(generateConfigMethod(annotation, method).build());
            }
        }
        return classBuilder.build();
    }

    private MethodSpec.Builder generateConfigMethod(TConfigProperty annotation,
                                                    ExecutableElement method) {
        TypeMirror returnType = method.getReturnType();

        TypeName returnTypeName = getTypeName(returnType);

        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getSimpleName().toString())
                .addParameter(Configuration.class, configParameterName)
                .addModifiers(Modifier.PUBLIC)
                .returns(returnTypeName);
        MethodImplementationGenerator methodGenerator = methodImplementationFactory.getMethodImplementationGeneratorByReturnType(returnTypeName);
        CodeBlock codeBlock = methodGenerator.codeBlock(annotation);
        methodBuilder.addCode(codeBlock);
        return methodBuilder;
    }

    private TypeName getTypeName(TypeMirror returnType) {
        TypeName returnTypeName;
        if (returnType.getKind().isPrimitive()) {
            returnTypeName = TypeName.get(returnType);
        } else {
            returnTypeName = ClassName.get(returnType);
        }
        return returnTypeName;
    }
}
