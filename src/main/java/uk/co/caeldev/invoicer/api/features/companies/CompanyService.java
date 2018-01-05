package uk.co.caeldev.invoicer.api.features.companies;

import uk.co.caeldev.invoicer.api.features.common.domain.Bank;

import java.util.Optional;
import java.util.UUID;

public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyFactory companyFactory;

    public CompanyService(final CompanyRepository companyRepository,
                          final CompanyFactory companyFactory) {

        this.companyRepository = companyRepository;
        this.companyFactory = companyFactory;
    }

    public Company create(final String name,
                          final String address,
                          final Bank bank,
                          final String postCode,
                          final String vatNumber) {
        final Company companyToBeSaved = companyFactory.getInstance(name, address, bank,
                postCode, vatNumber);
        return companyRepository.save(companyToBeSaved);
    }

    public Company update(final UUID companyGuid,
                          final String name,
                          final String address,
                          final Bank bank,
                          final String postCode,
                          final String vatNumber) {

        final Optional<Company> company = companyRepository.findByGuid(companyGuid);

        if (!company.isPresent()) {
            return create(name, address, bank, postCode, vatNumber);
        }

        companyFactory.getInstance()
        return null;
    }
}
