package tconfig.example;


import tconfig.spi.Configuration;

public class SystemPropertiesExample {

    public static void main(String[] args) {

        ConfigImpl config = new ConfigImpl();

        String beforeInitValue = ExampleConfiguration.CONFIG.testProperty(config);
        assert "TEST".equals(beforeInitValue);
        System.setProperty("tconfig.test.string.property", "TEST FROM MAIN");
        assert "TEST FROM MAIN".equals(beforeInitValue);
        System.out.printf("Success system");

        int testIntValue = ExampleConfiguration.CONFIG.testIntProperty(config);
        assert testIntValue == 10;
    }

    private static class ConfigImpl implements Configuration {

        @Override
        public String getString(String key) {
            return System.getProperty(key);
        }

        @Override
        public String getString(String key, String defaultValue) {
            return System.getProperty(key, defaultValue);
        }

        @Override
        public int getInt(String key) {
            return Integer.parseInt(System.getProperty(key));
        }

        @Override
        public int getInt(String key, int defaultValue) {
            return Integer.parseInt(System.getProperty(key, String.valueOf(defaultValue)));
        }

        @Override
        public long getLong(String key) {
            return Long.parseLong(System.getProperty(key));
        }

        @Override
        public long getLong(String key, long defaultValue) {
            return Long.parseLong(System.getProperty(key, String.valueOf(defaultValue)));
        }

        @Override
        public boolean getBoolean(String key) {
            return Boolean.parseBoolean(System.getProperty(key));
        }

        @Override
        public boolean getBoolean(String key, boolean defaultValue) {
            return Boolean.parseBoolean(System.getProperty(key, String.valueOf(defaultValue)));
        }
    }
}
