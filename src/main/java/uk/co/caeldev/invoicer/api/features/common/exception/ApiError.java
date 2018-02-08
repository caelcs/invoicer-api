package uk.co.caeldev.invoicer.api.features.common.exception;

public class ApiError {

    private String code;
    private String message;

    public ApiError(final ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
