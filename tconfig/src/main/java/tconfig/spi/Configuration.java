package tconfig.spi;

/**
 * Defines the contract for typed configuration
 */
public interface Configuration {

    /**
     * Get a string value by given configuration key.
     *
     * @param key The configuration key.
     * @return String value.
     */
    String getString(String key);

    /**
     * Get a string value by given configuration key.
     *
     * @param key          The configuration key.
     * @param defaultValue The default value.
     * @return String value.
     */
    String getString(String key, String defaultValue);


    /**
     * Get a int value by given configuration key.
     *
     * @param key The configuration key.
     * @return Int value.
     */
    int getInt(String key);

    /**
     * Get a int value by given configuration key.
     *
     * @param key          The configuration key.
     * @param defaultValue The default value.
     * @return Int value.
     */
    int getInt(String key, int defaultValue);


    /**
     * Get a long value by given configuration key.
     *
     * @param key The configuration key.
     * @return Long value.
     */
    long getLong(String key);

    /**
     * Get a long value by given configuration key.
     *
     * @param key          The configuration key.
     * @param defaultValue The default value.
     * @return Long value.
     */
    long getLong(String key, long defaultValue);


    /**
     * Get a boolean value by given configuration key.
     *
     * @param key The configuration key.
     * @return Boolean value.
     */
    boolean getBoolean(String key);

    /**
     * Get a boolean value by given configuration key.
     *
     * @param key          The configuration key.
     * @param defaultValue The default value.
     * @return Boolean value.
     */
    boolean getBoolean(String key, boolean defaultValue);

}