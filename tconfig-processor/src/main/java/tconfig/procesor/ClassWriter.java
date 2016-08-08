package tconfig.procesor;


import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import java.io.IOException;
import java.util.Objects;

class ClassWriter {

    private final Filer filer;

    ClassWriter(ProcessingEnvironment processingEnv) {
        this.filer = processingEnv.getFiler();
    }

    void writeClass(String packageName, TypeSpec typeSpec) throws IOException {
        Objects.requireNonNull(packageName);
        Objects.requireNonNull(typeSpec);
        JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
    }
}
