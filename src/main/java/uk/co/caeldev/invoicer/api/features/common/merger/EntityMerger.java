package uk.co.caeldev.invoicer.api.features.common.merger;

import java.util.Map;
import java.util.Optional;

public class EntityMerger {

    private final Map<Class, Merger> mergers;

    public EntityMerger(final Map<Class, Merger> mergers) {
        this.mergers = mergers;
    }

    public <T> T merge(T source, T target, Class<T> clazz) {
        final Optional<Merger<T>> merger = Optional.of(mergers.get(clazz));
        if (!merger.isPresent()) {
            throw new IllegalArgumentException();
        }

        return merger.get().merge(source, target);
    }
}
