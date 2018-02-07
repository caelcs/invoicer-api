package uk.co.caeldev.invoicer.api.features.customers;

import java.util.UUID;

public class CustomerFactory {

    public Customer getInstance(final String name,
                                final String address,
                                final String postCode,
                                final String vatNumber) {

        return new Customer(UUID.randomUUID(), name, address, postCode, vatNumber);
    }
}
