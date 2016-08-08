package tconfig.procesor.method;


import com.squareup.javapoet.TypeName;

import java.util.HashMap;
import java.util.Map;

public class MethodImplementationFactory {

    private final Map<String, MethodImplementationGenerator> mapping = new HashMap<>();

    public MethodImplementationFactory() {
        IntMethodImplementation intMethodImplementation = new IntMethodImplementation();
        mapping.put("int", intMethodImplementation);
        mapping.put("java.lang.Integer", intMethodImplementation);

        LongMethodImplementation longMethodImplementation = new LongMethodImplementation();
        mapping.put("long", longMethodImplementation);
        mapping.put("java.lang.Long", longMethodImplementation);

        mapping.put("java.lang.String", new StringMethodImplementation());
    }

    public MethodImplementationGenerator getMethodImplementationGeneratorByReturnType(TypeName typeName) {
        MethodImplementationGenerator methodImplementationGenerator = mapping.get(typeName.toString());
        if (methodImplementationGenerator == null) {
            throw new IllegalArgumentException(typeName.toString());
        }
        return methodImplementationGenerator;
    }
}
