package ru.fourqube.antigate;

import java.util.Properties;

/**
 * Настройки antigate.com
 *
 * @author Konstantin Pavlov
 * @since Mar 18, 2014
 */
public class AntigateSettings extends Properties {
    public static final String HOST = "antigate.host";
    public static final String KEY = "antigate.key";
    public static final String RUSSIAN = "antigate.russian";
    public static final String MAX_LENGTH = "antigate.maxLength";
    public static final String MIN_LENGTH = "antigate.minLength";
    public static final String CALC = "antigate.calc";
    public static final String NUMERIC = "antigate.numeric";
    public static final String REGISTER_SENSITIVE = "antigate.registerSensitive";
    public static final String TWO_WORDS = "antigate.twoWords";

    public AntigateSettings() {
        // todo set default settings
        put(HOST, "antigate.com");
        put(TWO_WORDS, "0");
        put(NUMERIC, "0");
        put(CALC, "0");
        put(MIN_LENGTH, "0");
        put(MAX_LENGTH, "0");
        put(RUSSIAN, "0");
    }

    public AntigateSettings(Properties properties) {
        super(properties);
    }

    public String getHost() {
        return getProperty(HOST);
    }

    public String getKey() {
        return getProperty(KEY);
    }

    public String isRussian() {
        return getProperty(RUSSIAN);
    }

    public String getMaxLength() {
        return getProperty(MAX_LENGTH);
    }

    public String getMinLength() {
        return getProperty(MIN_LENGTH);
    }

    public String isCalc() {
        return getProperty(CALC);
    }

    public String getNumeric() {
        return getProperty(NUMERIC);
    }

    public String isRegisterSensitive() {
        return getProperty(REGISTER_SENSITIVE);
    }

    public String isTwoWords() {
        return getProperty(TWO_WORDS);
    }
}
