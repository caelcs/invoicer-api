package uk.co.caeldev.invoicer.api.features.companies;

import static uk.org.fyodor.generators.RDG.string;

public class TestBankBuilder {

    TestBankBuilder() {
    }

    public static TestBankBuilder newBuilder() {
        return new TestBankBuilder();
    }

    public Bank build() {
        return new Bank(string().next(), string().next(), string().next());
    }
}
