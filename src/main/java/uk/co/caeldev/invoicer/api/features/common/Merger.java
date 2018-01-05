package uk.co.caeldev.invoicer.api.features.common;

public interface Merger<T> {

    Class<T> getClazz();

    T merge(T source, T target);
}
