package uk.co.caeldev.invoicer.api.features.companies;

import static uk.org.fyodor.generators.RDG.postcode;
import static uk.org.fyodor.generators.RDG.string;

public class TestCompanyRequestBuilder {

    private String name;
    private String address;
    private String postCode;
    private String vatNumber;
    private Bank bank;

    TestCompanyRequestBuilder() {
    }

    public static TestCompanyRequestBuilder newBuilder() {
        return new TestCompanyRequestBuilder();
    }

    public CompanyRequest build() {
        name = string().next();
        address = string().next();
        postCode = postcode().next();
        vatNumber = string().next();
        bank = TestBankBuilder.newBuilder().build();
        return new CompanyRequest(name, address, postCode,
                vatNumber, bank);
    }
}
