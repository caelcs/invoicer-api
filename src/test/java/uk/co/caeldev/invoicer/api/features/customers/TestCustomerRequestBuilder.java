package uk.co.caeldev.invoicer.api.features.customers;

import static uk.org.fyodor.generators.RDG.postcode;
import static uk.org.fyodor.generators.RDG.string;

public class TestCustomerRequestBuilder {

    private String name;
    private String address;
    private String postCode;
    private String vatNumber;

    public static TestCustomerRequestBuilder newBuilder() {
        return new TestCustomerRequestBuilder();
    }

    public CustomerRequest build() {
        name = string().next();
        address = string().next();
        postCode = postcode().next();
        vatNumber = string().next();
        return new CustomerRequest(name, address, postCode,
                vatNumber);
    }
}
