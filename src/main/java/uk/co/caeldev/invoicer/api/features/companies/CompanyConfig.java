package uk.co.caeldev.invoicer.api.features.companies;

import org.mongodb.morphia.Datastore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.caeldev.invoicer.api.features.common.merger.EntityMerger;

@Configuration
public class CompanyConfig {

    @Bean
    public CompanyRepository companyRepository(final Datastore datastore) {
        return new CompanyRepository(datastore);
    }

    @Bean
    public CompanyFactory companyFactory() {
        return new CompanyFactory();
    }

    @Bean
    public CompanyService companyService(final CompanyRepository companyRepository,
                                         final CompanyFactory companyFactory,
                                         final EntityMerger entityMerger) {
        return new CompanyService(companyRepository, companyFactory, entityMerger);
    }

}
