package ru.fourqube.antigate;

import java.io.Serializable;

/**
 * @author Konstantin Pavlov
 * @since Mar 18, 2014
 */
public class CaptchaStatus implements Serializable {
    private String text;
    private boolean ready;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
