package ru.fourqube.antigate.exceptions;

/**
 * @author Konstantin Pavlov
 * @since Mar 18, 2014
 */
public class AntigateException extends RuntimeException {
    private AntigateCode errorCode;

    public AntigateException(AntigateCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public AntigateException(String message, AntigateCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AntigateException(String message, Throwable cause) {
        super(message, cause);
    }

    public AntigateCode getErrorCode() {
        return errorCode;
    }
}
