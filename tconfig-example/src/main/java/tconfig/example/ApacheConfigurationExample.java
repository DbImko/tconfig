package tconfig.example;


import org.apache.commons.configuration.BaseConfiguration;
import tconfig.apache.commons.configuration.ApacheConfigurationWrapper;

public class ApacheConfigurationExample {

    public static void main(String[] args) {

        BaseConfiguration baseConfiguration = new BaseConfiguration();
        ApacheConfigurationWrapper configuration = new ApacheConfigurationWrapper(baseConfiguration);

        String beforeInitValue = ExampleConfiguration.CONFIG.testProperty(configuration);
        assert "TEST".equals(beforeInitValue);
        baseConfiguration.setProperty("tconfig.test.string.property", "TEST FROM MAIN");
        assert "TEST FROM MAIN".equals(beforeInitValue);
        System.out.printf("Success apache");
    }

}
