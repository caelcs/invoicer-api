package uk.co.caeldev.invoicer.api.features.customers;

import uk.co.caeldev.invoicer.api.features.customers.Customer.Builder;

import java.util.UUID;

import static uk.org.fyodor.generators.RDG.postcode;
import static uk.org.fyodor.generators.RDG.string;

public class TestCustomerBuilder extends Builder {

    TestCustomerBuilder() {
        withAddress(string().next());
        withGuid(UUID.randomUUID());
        withName(string().next());
        withPostCode(postcode().next());
        withVatNumber(string().next());
    }

    public static Builder newBuilder() {
        return new TestCustomerBuilder();
    }
}
