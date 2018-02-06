package uk.co.caeldev.invoicer.api.features.companies;

import uk.co.caeldev.invoicer.api.features.common.domain.Bank;

import java.util.UUID;

public class CompanyFactory {
    public Company getInstance(final String name,
                               final String address,
                               final Bank bank,
                               final String postCode,
                               final String vatNumber) {
        return new Company(UUID.randomUUID(), null, name,
                address, postCode, bank, vatNumber);
    }
}
