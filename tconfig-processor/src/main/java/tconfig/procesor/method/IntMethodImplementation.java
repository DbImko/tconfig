package tconfig.procesor.method;


import com.squareup.javapoet.CodeBlock;
import lombok.AllArgsConstructor;
import tconfig.annotation.TConfigProperty;

import static java.text.MessageFormat.format;

@AllArgsConstructor
class IntMethodImplementation implements MethodImplementationGenerator {

    private static final String STATEMENT_WITH_DEFAULT = "return {0}.getInt($S, Integer.parseInt($S))";
    private static final String STATEMENT_WITHOUT_DEFAULT = "return {0}.getInt($S)";

    private final String configParameterName;

    IntMethodImplementation() {
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
