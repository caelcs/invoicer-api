package uk.co.caeldev.invoicer.api.features.common.utils;

import org.springframework.beans.BeanUtils;

public class EntityMerger {

    public <T> T merge(T source, T target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }

}
