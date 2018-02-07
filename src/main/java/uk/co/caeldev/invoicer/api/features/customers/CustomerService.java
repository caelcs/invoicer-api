package uk.co.caeldev.invoicer.api.features.customers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerService {

    final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final CustomerRepository customerRepository;

    private final CustomerFactory customerFactory;

    public CustomerService(final CustomerRepository customerRepository,
                           final CustomerFactory customerFactory) {

        this.customerRepository = customerRepository;
        this.customerFactory = customerFactory;
    }

    public Customer create(final String name,
                           final String address,
                           final String postCode,
                           final String vatNumber) {
        LOGGER.info("Creating new customer");
        final Customer customer = customerFactory.getInstance(name, address, postCode, vatNumber);
        return customerRepository.save(customer);
    }
}
