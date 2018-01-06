package uk.co.caeldev.invoicer.api.features.common.merger;

import uk.co.caeldev.invoicer.api.features.companies.Company;

final class CompanyMerger implements Merger<Company> {

    @Override
    public Class<Company> getClazz() {
        return Company.class;
    }

    @Override
    public Company merge(final Company source,
                         final Company target) {
        return null;
    }
}
