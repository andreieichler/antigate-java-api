package ru.fourqube.antigate.exceptions;

/**
 * Типы ошибок antigate.com
 *
 * @author Konstantin Pavlov
 * @since Mar 18, 2014
 */
public enum AntigateCode {
    CAPTCHA_NOT_READY("Captcha not ready"),
    ERROR_WRONG_USER_KEY("User key has bad format"),
    ERROR_KEY_DOES_NOT_EXIST("Wrong user key"),
    ERROR_NO_SLOT_AVAILABLE("There are no free employees in antigate. You can increase bet"),
    ERROR_ZERO_CAPTCHA_FILESIZE("Captcha filesize zero"),
    ERROR_TOO_BIG_CAPTCHA_FILESIZE("Captcha size is more then 100 Kb"),
    ERROR_ZERO_BALANCE("User's balance is <=0$"),
    ERROR_IP_NOT_ALLOWED("Wrong ip. Can be changed in settings"),
    ERROR_CAPTCHA_UNSOLVABLE("Too hard captcha"),
    ERROR_BAD_DUPLICATES("Ends attempts in \"100% recognise mode\""),
    ERROR_NO_SUCH_METHOD("Put \"method\" parameter in request"),
    ERROR_IMAGE_TYPE_NOT_SUPPORTED("There is not JPEG, GIF or PNG format"),
    ERROR_WRONG_ID_FORMAT("There is captcha's ID format. Must include only digits"),
    UNKNOWN_EXCEPTION("Unknown Error");

    private final String message;

    AntigateCode(String message) {
        this.message = message;
    }

    public static AntigateCode fromString(String name) {
        AntigateCode errorCode = UNKNOWN_EXCEPTION;
        for (AntigateCode code : values()) {
            if (code.name().equals(name)) {
                return code;
            }
        }
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
