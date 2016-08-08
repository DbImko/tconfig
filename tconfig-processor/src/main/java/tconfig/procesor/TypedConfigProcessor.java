package tconfig.procesor;

import tconfig.annotation.TConfig;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static javax.lang.model.util.ElementFilter.typesIn;

@SupportedOptions({
        // TODO: add debug option
})
public final class TypedConfigProcessor extends AbstractProcessor {

    private final List<ConfigProcessor> processors;
    private Logger logger;

    public TypedConfigProcessor() {
        processors = new ArrayList<>();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        logger = new Logger(processingEnv.getMessager());
        processors.add(new ImplementationConfigProcessor(processingEnv, logger));
        // TODO: add documentation processor
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(TConfig.class.getName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        final Set<? extends TypeElement> interfaces = typesIn(roundEnv.getElementsAnnotatedWith(TConfig.class));

        for (TypeElement interfaceElement : interfaces) {
            if (interfaceElement.getKind().isInterface()
                    && !interfaceElement.getModifiers().contains(Modifier.PRIVATE)) {
                for (ConfigProcessor processor : processors) {
                    processor.processConfig(interfaceElement);
                }
            }
        }
        return true;
    }
}
