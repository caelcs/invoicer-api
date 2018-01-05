package uk.co.caeldev.invoicer.api.features.companies;

import uk.co.caeldev.invoicer.api.features.common.domain.Bank;

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

    public TestCompanyBuilder withGuid(final UUID guid) {
        this.guid = guid;
        return this;
    }

    public TestCompanyBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public TestCompanyBuilder withAddress(final String address) {
        this.address = address;
        return this;
    }

    public TestCompanyBuilder withPostCode(final String postCode) {
        this.postCode = postCode;
        return this;
    }

    public TestCompanyBuilder withBank(final Bank bank) {
        this.bank = bank;
        return this;
    }

    public TestCompanyBuilder withVatNumber(final String vatNumber) {
        this.vatNumber = vatNumber;
        return this;
    }

    public Company build() {
        return new Company(guid, username, name, address, postCode, bank, vatNumber);
    }
}
