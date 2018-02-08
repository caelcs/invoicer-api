package uk.co.caeldev.invoicer.api.features.common;

import uk.co.caeldev.invoicer.api.features.companies.Company;
import uk.co.caeldev.invoicer.api.features.companies.TestCompanyBuilder;
import uk.org.fyodor.generators.Generator;

public class Generators {

    public static Generator<Company> ofCompany() {
        return () -> TestCompanyBuilder.newBuilder().build();
    }

}
