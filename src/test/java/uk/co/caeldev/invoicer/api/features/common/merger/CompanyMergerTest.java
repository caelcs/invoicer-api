package uk.co.caeldev.invoicer.api.features.common.merger;

import org.junit.Before;
import org.junit.Test;
import uk.co.caeldev.invoicer.api.features.companies.Company;
import uk.co.caeldev.invoicer.api.features.companies.TestCompanyBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class CompanyMergerTest {

    private CompanyMerger companyMerger;

    @Before
    public void testee() {
        companyMerger = new CompanyMerger();
    }

    @Test
    public void shouldMergeCompany() {
        //Given
        final Company source = new Company(null, null, "test", null, null, null, null);
        final Company target = TestCompanyBuilder.newBuilder().build();

        //When
        final Company merged = companyMerger.merge(source, target);

        //Then
        assertThat(merged.getName()).isEqualTo("test");
        assertThat(merged.getBank()).isEqualTo(target.getBank());
        assertThat(merged.getAddress()).isEqualTo(target.getAddress());
        assertThat(merged.getVatNumber()).isEqualTo(target.getVatNumber());
        assertThat(merged.getPostCode()).isEqualTo(target.getPostCode());
        assertThat(merged.getUsername()).isEqualTo(target.getUsername());
        assertThat(merged.getGuid()).isEqualTo(target.getGuid());
        assertThat(merged.getId()).isEqualTo(target.getId());
    }
}