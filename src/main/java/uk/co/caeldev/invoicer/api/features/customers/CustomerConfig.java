package uk.co.caeldev.invoicer.api.features.customers;

import org.mongodb.morphia.Datastore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.caeldev.invoicer.api.features.common.utils.EntityMerger;

@Configuration
public class CustomerConfig {

    @Bean
    public CustomerRepository customerRepository(final Datastore datastore) {
        return new CustomerRepository(datastore);
    }

    @Bean
    public CustomerFactory customerFactory() {
        return new CustomerFactory();
    }

    @Bean
    public CustomerService customerService(final CustomerRepository customerRepository,
                                           final CustomerFactory customerFactory,
                                           final EntityMerger entityMerger) {
        return new CustomerService(customerRepository, customerFactory, entityMerger);
    }

}
