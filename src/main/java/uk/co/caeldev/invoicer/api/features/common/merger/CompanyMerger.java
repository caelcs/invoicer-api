package uk.co.caeldev.invoicer.api.features.common.merger;

import org.springframework.beans.BeanUtils;
import uk.co.caeldev.invoicer.api.features.companies.Company;

final class CompanyMerger implements Merger<Company> {

    @Override
    public Class<Company> getClazz() {
        return Company.class;
    }

    @Override
    public Company merge(Company source,
                         Company target) {

        BeanUtils.copyProperties(source, target);
        return target;
    }
}
