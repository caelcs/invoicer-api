package uk.co.caeldev.invoicer.api.features.companies;

import uk.co.caeldev.invoicer.api.features.common.domain.Bank;

import java.util.UUID;

public class CompanyResourceBuilder {

    private UUID guid;
    private String name;
    private String address;
    private String postCode;
    private Bank bank;
    private String vatNumber;

    CompanyResourceBuilder() {
    }

    public static CompanyResourceBuilder newBuilder() {
        return new CompanyResourceBuilder();
    }

    public CompanyResource build() {
        return new CompanyResource(guid, name, address, postCode, vatNumber, bank);
    }

    public CompanyResourceBuilder withCompany(Company company) {
        this.guid = company.getGuid();
        this.name = company.getName();
        this.address = company.getAddress();
        this.postCode = company.getPostCode();
        this.bank = company.getBank();
        this.vatNumber = company.getVatNumber();
        return this;
    }
}
