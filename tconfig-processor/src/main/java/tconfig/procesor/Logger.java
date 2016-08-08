package tconfig.procesor;


import lombok.SneakyThrows;

import javax.annotation.processing.Messager;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

final class Logger {

    private final Messager messager;

    Logger(Messager messager) {
        Objects.requireNonNull(messager);
        this.messager = messager;
    }

    void debug(String message) {
        messager.printMessage(Diagnostic.Kind.OTHER, message);
    }

    void error(TypeElement interfaceElement, Throwable cause) {
        String stringCause = exceptionToString(cause);
        messager.printMessage(Diagnostic.Kind.ERROR, stringCause, interfaceElement);
    }

    @SneakyThrows({IOException.class})
    private static String exceptionToString(final Throwable cause) {
        final StringWriter stringWriter = new StringWriter();
        try (PrintWriter printWriter = new PrintWriter(stringWriter, true)) {
            cause.printStackTrace(printWriter);
            stringWriter.flush();
            stringWriter.close();
        }
        return stringWriter.toString();
    }
}
