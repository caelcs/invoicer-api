package uk.co.caeldev.invoicer.api.features.companies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.caeldev.invoicer.api.features.common.domain.Bank;
import uk.co.caeldev.invoicer.api.features.common.utils.EntityMerger;

import java.util.Optional;
import java.util.UUID;

public class CompanyService {

    final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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

        LOGGER.info("updating company guid {}", companyGuid);

        final Optional<Company> company = companyRepository.findLatestByGuid(companyGuid);

        if (!company.isPresent()) {
            return create(name, address, bank, postCode, vatNumber);
        }

        final Company companyToBeSaved = companyFactory.getInstance(name,
                address, bank, postCode, vatNumber);

        final Company merged = entityMerger.merge(companyToBeSaved, company.get());
        LOGGER.info("company merged: {}", merged);

        return companyRepository.save(merged);
    }

    public Optional<Company> findLatestByGuid(final UUID companyGuid) {
        return companyRepository.findLatestByGuid(companyGuid);
    }

    public void delete(final Company company) {
        companyRepository.delete(company);
    }
}
