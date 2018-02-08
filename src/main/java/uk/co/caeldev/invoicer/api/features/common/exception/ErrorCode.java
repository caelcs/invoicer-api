package uk.co.caeldev.invoicer.api.features.common.exception;

public enum ErrorCode {
    NOT_FOUND("1-0", "Entity not found");

    private final String code;
    private final String message;

    ErrorCode(final String code,
              final String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
