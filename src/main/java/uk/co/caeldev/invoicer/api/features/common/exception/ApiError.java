package uk.co.caeldev.invoicer.api.features.common.exception;

public class ApiError {

    private String code;
    private String message;
    private final String description;

    public ApiError(final ErrorCode errorCode, String description) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
