package uk.co.caeldev.invoicer.api.features.companies;

import uk.co.caeldev.invoicer.api.features.common.domain.Bank;
import uk.co.caeldev.invoicer.api.features.common.merger.EntityMerger;

import java.util.Optional;
import java.util.UUID;

public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyFactory companyFactory;
    private final EntityMerger entityMerger;

    public CompanyService(final CompanyRepository companyRepository,
                          final CompanyFactory companyFactory, EntityMerger entityMerger) {

        this.companyRepository = companyRepository;
        this.companyFactory = companyFactory;
        this.entityMerger = entityMerger;
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

        final Company companyToBeSaved = companyFactory.getInstance(name,
                address, bank, postCode, vatNumber);

        final Company merged = entityMerger.merge(company.get(), companyToBeSaved,
                Company.class);

        return companyRepository.save(merged);
    }
}
