package uk.co.caeldev.invoicer.api.features.common.merger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class MergerConfig {

    @Bean
    public CompanyMerger companyMerger() {
        return new CompanyMerger();
    }

    @Bean
    public EntityMerger entityMerger(final List<Merger> mergers) {
        final Map<Class, Merger> mergersMapper = mergers.stream()
                .collect(Collectors.toMap(entity -> entity.getClazz(),
                        entity -> entity));
        return new EntityMerger(mergersMapper);
    }

}
