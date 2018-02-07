package uk.co.caeldev.invoicer.api.features.customers;

import org.mongodb.morphia.Datastore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    public CustomerRepository customerRepository(final Datastore datastore) {
        return new CustomerRepository(datastore);
    }

}
