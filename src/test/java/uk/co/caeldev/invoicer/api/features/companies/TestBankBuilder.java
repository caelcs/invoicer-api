package uk.co.caeldev.invoicer.api.features.companies;

import uk.co.caeldev.invoicer.api.features.common.domain.Bank;

import static uk.org.fyodor.generators.RDG.string;

public class TestBankBuilder {

    private String name;
    private String accountNumber;
    private String sortCode;

    TestBankBuilder() {
    }

    public static TestBankBuilder newBuilder() {
        return new TestBankBuilder();
    }

    public Bank build() {
        name = string().next();
        accountNumber = string().next();
        sortCode = string().next();
        return new Bank(name, accountNumber, sortCode);
    }
}
