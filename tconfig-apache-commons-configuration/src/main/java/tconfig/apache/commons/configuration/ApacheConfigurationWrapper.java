package tconfig.apache.commons.configuration;


import tconfig.spi.Configuration;

import java.util.Objects;

public class ApacheConfigurationWrapper implements Configuration {

    private org.apache.commons.configuration.Configuration delegate;

    public ApacheConfigurationWrapper(org.apache.commons.configuration.Configuration configuration) {
        Objects.requireNonNull(configuration);
        delegate = configuration;
    }

    @Override
    public String getString(String key) {
        return delegate.getString(key);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return delegate.getString(key, defaultValue);
    }

    @Override
    public int getInt(String key) {
        return delegate.getInt(key);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return delegate.getInt(key, defaultValue);
    }

    @Override
    public long getLong(String key) {
        return delegate.getLong(key);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return delegate.getLong(key, defaultValue);
    }

    @Override
    public boolean getBoolean(String key) {
        return delegate.getBoolean(key);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return delegate.getBoolean(key, defaultValue);
    }
}
