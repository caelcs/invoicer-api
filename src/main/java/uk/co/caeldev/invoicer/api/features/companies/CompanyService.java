package uk.co.caeldev.invoicer.api.features.companies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.caeldev.invoicer.api.features.common.domain.Bank;
import uk.co.caeldev.invoicer.api.features.common.exception.ObjectNotFoundException;
import uk.co.caeldev.invoicer.api.features.common.utils.EntityMerger;

import java.text.MessageFormat;
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
        LOGGER.info("Create company");

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

        LOGGER.info("updating company by guid {}", companyGuid);

        final Company company = findLatestByGuid(companyGuid);

        final Company companyToBeSaved = companyFactory.getInstance(name,
                address, bank, postCode, vatNumber);

        final Company merged = entityMerger.merge(companyToBeSaved, company);
        LOGGER.info("company merged: {}", merged);

        return companyRepository.save(merged);
    }

    public Company findLatestByGuid(final UUID companyGuid) {
        LOGGER.info("find company by guid {}", companyGuid);

        final Optional<Company> latestByGuid = companyRepository.findLatestByGuid(companyGuid);

        if (!latestByGuid.isPresent()) {
            final String message = MessageFormat.format("Company does not exists for guid {0}", companyGuid);
            throw new ObjectNotFoundException(message);
        }

        return latestByGuid.get();
    }

    public void delete(final UUID companyGuid) {
        LOGGER.info("delete company by guid {}", companyGuid);

        final Company latestByGuid = findLatestByGuid(companyGuid);

        companyRepository.delete(latestByGuid);
    }
}
