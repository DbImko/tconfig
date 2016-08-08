package tconfig.procesor;

import com.squareup.javapoet.TypeSpec;
import tconfig.procesor.method.MethodImplementationFactory;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.io.IOException;

public class ImplementationConfigProcessor implements ConfigProcessor {
//    private final ProcessingEnvironment processingEnv;

    private final Logger logger;
    private Elements elementUtils;
    private ClassWriter classWriter;
    private final MethodImplementationFactory methodImplementationFactory;

    ImplementationConfigProcessor(ProcessingEnvironment processingEnv, Logger logger) {
//        this.processingEnv = processingEnv;
        this.logger = logger;
        this.classWriter = new ClassWriter(processingEnv);
        methodImplementationFactory = new MethodImplementationFactory();
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public void processConfig(TypeElement interfaceElement) {
        try {
            ConfigClassGenerator classModel = new ConfigClassGenerator(logger, interfaceElement, elementUtils, methodImplementationFactory);
            TypeSpec typeSpec = classModel.generateConfigClass();
            classWriter.writeClass(classModel.getPackageName(), typeSpec);
        } catch (RuntimeException | IOException e) {
            logger.error(interfaceElement, e);
        }
    }
}
