package ru.fourqube.antigate;

/**
 * @author Konstantin Pavlov
 * @since Mar 18, 2014
 */
public class AntigateClientBuilder {
    private AntigateSettings settings = new AntigateSettings();

    public static AntigateClientBuilder create() {
        return new AntigateClientBuilder();
    }

    public AntigateClientBuilder setSettings(AntigateSettings settings) {
        this.settings = settings;
        return this;
    }

    public AntigateClientBuilder setRussian(boolean russian) {
        settings.setProperty(AntigateSettings.RUSSIAN, russian ? "1" : "0");
        return this;
    }

    public AntigateClientBuilder setKey(String key) {
        settings.setProperty(AntigateSettings.KEY, key);
        return this;
    }

    public AntigateClient build() {
        return new AntigateClientImpl(settings);
    }
}
