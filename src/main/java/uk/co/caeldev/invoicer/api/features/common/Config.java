package uk.co.caeldev.invoicer.api.features.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.caeldev.invoicer.api.features.common.utils.EntityMerger;

@Configuration
public class Config {

    @Bean
    public EntityMerger entityMerger() {
        return new EntityMerger();
    }

}
