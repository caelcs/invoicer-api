package uk.co.caeldev.invoicer.api.features.companies;

import java.util.UUID;

import static uk.org.fyodor.generators.RDG.postcode;
import static uk.org.fyodor.generators.RDG.string;

public class TestCompanyBuilder {

    private UUID guid = UUID.randomUUID();
    private String name = string().next();
    private String address = string().next();
    private String postCode = postcode().next();
    private Bank bank = TestBankBuilder.newBuilder().build();
    private String vatNumber = string().next();
    private String username =string().next();

    TestCompanyBuilder() {
    }

    public static TestCompanyBuilder newBuilder() {
        return new TestCompanyBuilder();
    }

    public Company build() {
        return new Company(guid, username, name, address, postCode, bank, vatNumber);
    }
}
