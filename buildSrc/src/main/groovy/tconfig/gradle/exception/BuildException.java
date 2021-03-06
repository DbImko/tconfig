package tconfig.gradle.exception;

import org.gradle.tooling.GradleConnectionException;

public class BuildException extends GradleConnectionException {
    public BuildException(String message) {
        super(message);
    }

    public BuildException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
