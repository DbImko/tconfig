package tconfig.procesor.method;


import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;
import tconfig.annotation.TConfigProperty;

public interface MethodImplementationGenerator {

    String DEFAULT_CONFIG_PARAMETER_NAME = "config";

    CodeBlock codeBlock(TConfigProperty annotation);

}
