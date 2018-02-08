package uk.co.caeldev.invoicer.api.features.customers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.caeldev.invoicer.api.features.common.exception.ObjectNotFoundException;
import uk.co.caeldev.invoicer.api.features.common.utils.EntityMerger;

import java.util.Optional;
import java.util.UUID;

public class CustomerService {

    final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final CustomerRepository customerRepository;

    private final CustomerFactory customerFactory;

    private final EntityMerger entityMerger;

    public CustomerService(final CustomerRepository customerRepository,
                           final CustomerFactory customerFactory,
                           final EntityMerger entityMerger) {

        this.customerRepository = customerRepository;
        this.customerFactory = customerFactory;
        this.entityMerger = entityMerger;
    }

    public Customer create(final String name,
                           final String address,
                           final String postCode,
                           final String vatNumber) {
        LOGGER.info("Creating new customer");
        final Customer customer = customerFactory.getInstance(name, address, postCode, vatNumber);
        return customerRepository.save(customer);
    }

    public Customer update(final UUID customerGuid,
                           final String name,
                           final String address,
                           final String postCode,
                           final String vatNumber) {

        final Optional<Customer> latestByGuid = customerRepository.findLatestByGuid(customerGuid);

        if (!latestByGuid.isPresent()) {
            throw new ObjectNotFoundException();
        }

        final Customer customerToBeMerge = customerFactory.getInstance(name, address, postCode, vatNumber);

        final Customer customerToBeSaved = entityMerger.merge(customerToBeMerge, latestByGuid.get());

        return customerRepository.save(customerToBeSaved);

    }
}
