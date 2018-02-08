package uk.co.caeldev.invoicer.api.features.common;

import uk.co.caeldev.invoicer.api.features.companies.Company;
import uk.co.caeldev.invoicer.api.features.companies.TestCompanyBuilder;
import uk.co.caeldev.invoicer.api.features.customers.Customer;
import uk.co.caeldev.invoicer.api.features.customers.TestCustomerBuilder;
import uk.org.fyodor.generators.Generator;

public class Generators {

    public static Generator<Company> ofCompany() {
        return () -> TestCompanyBuilder.newBuilder().build();
    }

    public static Generator<Customer> ofCustomer() {
        return () -> TestCustomerBuilder.newBuilder().build();
    }

}
