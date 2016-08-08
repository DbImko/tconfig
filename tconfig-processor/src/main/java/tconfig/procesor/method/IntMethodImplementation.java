package tconfig.procesor.method;


import com.squareup.javapoet.CodeBlock;
import lombok.AllArgsConstructor;
import tconfig.annotation.TConfigProperty;

import static java.text.MessageFormat.format;

@AllArgsConstructor
class IntMethodImplementation implements MethodImplementationGenerator {

    private final String configParameterName;

    IntMethodImplementation() {
        this(DEFAULT_CONFIG_PARAMETER_NAME);
    }

    @Override
    public CodeBlock codeBlock(TConfigProperty annotation) {
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        String statement = format("return {0}.getInt($S, Integer.parseInt($S))", configParameterName);
        codeBlockBuilder.addStatement(statement, annotation.key(), annotation.defaultValue());
        return codeBlockBuilder.build();
    }
}
