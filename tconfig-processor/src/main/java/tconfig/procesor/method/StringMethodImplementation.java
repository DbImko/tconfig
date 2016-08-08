package tconfig.procesor.method;


import com.squareup.javapoet.CodeBlock;
import lombok.AllArgsConstructor;
import tconfig.annotation.TConfigProperty;

import static java.text.MessageFormat.format;

@AllArgsConstructor
class StringMethodImplementation implements MethodImplementationGenerator {

    private final String configParameterName;

    StringMethodImplementation() {
        this(DEFAULT_CONFIG_PARAMETER_NAME);
    }

    @Override
    public CodeBlock codeBlock(TConfigProperty annotation) {
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        String statement = format("return {0}.getString($S, $S)", configParameterName);
        codeBlockBuilder.addStatement(statement, annotation.key(), annotation.defaultValue());
        return codeBlockBuilder.build();
    }
}
