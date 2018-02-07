package uk.co.caeldev.invoicer.api.features.customers;

import java.util.UUID;

public class CustomerResourceBuilder {
    private UUID guid;
    private String name;
    private String address;
    private String postCode;
    private String vatNumber;

    CustomerResourceBuilder() {
    }

    public static CustomerResourceBuilder newBuilder() {
        return new CustomerResourceBuilder();
    }

    public CustomerResource build() {
        return new CustomerResource(guid, name, address, postCode, vatNumber);
    }

    public CustomerResourceBuilder withCustomer(final Customer customer) {
        this.guid = customer.getGuid();
        this.name = customer.getName();
        this.address = customer.getAddress();
        this.postCode = customer.getPostCode();
        this.vatNumber = customer.getVatNumber();
        return this;
    }
}
