package uk.co.caeldev.invoicer.api.features.companies;

import uk.co.caeldev.invoicer.api.features.common.Merger;

public final class CompanyMerger implements Merger<Company> {

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
