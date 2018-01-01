package uk.co.caeldev.invoicer.api.features.companies;

import static uk.org.fyodor.generators.RDG.string;

public class TestCompanyRequestBuilder {

    TestCompanyRequestBuilder() {
    }

    public static TestCompanyRequestBuilder newBuilder() {
        return new TestCompanyRequestBuilder();
    }

    public CompanyRequest build() {
        return new CompanyRequest(string().next(), string().next(), string().next(),
                string().next(), TestBankBuilder.newBuilder().build());
    }
}
