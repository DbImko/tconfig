package tconfig.procesor.method;


import com.squareup.javapoet.CodeBlock;
import lombok.AllArgsConstructor;
import tconfig.annotation.TConfigProperty;

import static java.text.MessageFormat.format;

@AllArgsConstructor
class LongMethodImplementation implements MethodImplementationGenerator {

    private final String configParameterName;

    LongMethodImplementation() {
        this(DEFAULT_CONFIG_PARAMETER_NAME);
    }

    @Override
    public CodeBlock codeBlock(TConfigProperty annotation) {
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        String statement = format("return rooxConfig.getLong($S, Long.parseLong($S))", configParameterName);
        codeBlockBuilder.addStatement(statement, annotation.key(), annotation.defaultValue());
        return codeBlockBuilder.build();
    }
}
