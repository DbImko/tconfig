package tconfig.procesor.method;


import com.squareup.javapoet.CodeBlock;
import lombok.AllArgsConstructor;
import tconfig.annotation.TConfigProperty;

import static java.text.MessageFormat.format;

@AllArgsConstructor
class StringMethodImplementation implements MethodImplementationGenerator {

    private static final String STATEMENT_WITH_DEFAULT = "return {0}.getString($S, $S)";
    private static final String STATEMENT_WITHOUT_DEFAULT = "return {0}.getString($S)";

    private final String configParameterName;

    StringMethodImplementation() {
        this(DEFAULT_CONFIG_PARAMETER_NAME);
    }

    @Override
    public CodeBlock codeBlock(TConfigProperty annotation) {
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        String defaultValue = annotation.defaultValue();

        if (TConfigProperty.DEFAULT_NONE.equals(defaultValue)) {
            String statement = format(STATEMENT_WITHOUT_DEFAULT, configParameterName);
            codeBlockBuilder.addStatement(statement, annotation.key());
        } else {
            String statement = format(STATEMENT_WITH_DEFAULT, configParameterName);
            codeBlockBuilder.addStatement(statement, annotation.key(), defaultValue);
        }

        return codeBlockBuilder.build();
    }
}
