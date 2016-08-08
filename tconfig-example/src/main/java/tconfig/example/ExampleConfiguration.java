package tconfig.example;

import tconfig.spi.Configuration;
import tconfig.TConfigFactory;
import tconfig.annotation.TConfig;
import tconfig.annotation.TConfigProperty;

@TConfig
public interface ExampleConfiguration {

    ExampleConfiguration CONFIG = TConfigFactory.createConfigFor(ExampleConfiguration.class);

    @TConfigProperty(key = "tconfig.test.int.property", defaultValue = "10")
    int testIntProperty(Configuration configuration);

    @TConfigProperty(key = "tconfig.test.string.property", defaultValue = "TEST")
    String testProperty(Configuration configuration);
}
