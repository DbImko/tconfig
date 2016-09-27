package tconfig.example;

import tconfig.TConfigFactory;
import tconfig.annotation.TConfig;
import tconfig.annotation.TConfigProperty;
import tconfig.spi.Configuration;

@TConfig
public interface ExampleConfiguration {

    ExampleConfiguration CONFIG = TConfigFactory.createConfigFor(ExampleConfiguration.class);

    @TConfigProperty(key = "tconfig.test.int.property", defaultValue = "10")
    int testIntProperty(Configuration configuration);

    @TConfigProperty(key = "tconfig.test.string.property", defaultValue = "TEST")
    String testProperty(Configuration configuration);

    @TConfigProperty(key = "tconfig.test.string.property1")
    String testPropertyWithEmptyDefault(Configuration configuration);
}
