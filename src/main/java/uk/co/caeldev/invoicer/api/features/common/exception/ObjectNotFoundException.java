package uk.co.caeldev.invoicer.api.features.common.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(final String message) {
        super(message);
    }

}
