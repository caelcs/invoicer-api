package uk.co.caeldev.invoicer.api.features.companies;

import org.junit.Before;
import org.junit.Test;
import uk.co.caeldev.invoicer.api.features.common.domain.Bank;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.org.fyodor.generators.RDG.postcode;
import static uk.org.fyodor.generators.RDG.string;

public class CompanyFactoryTest {

    private CompanyFactory companyFactory;

    @Before
    public void testee() {
        companyFactory = new CompanyFactory();
    }

    @Test
    public void shouldCreateCompany() {
        //Given
        final String name = string().next();
        final String address = string().next();
        final Bank bank = TestBankBuilder.newBuilder().build();
        final String postCode = postcode().next();
        final String vatNumber = string().next();

        //When
        final Company result = this.companyFactory.getInstance(name, address, bank, postCode, vatNumber);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getAddress()).isEqualTo(address);
        assertThat(result.getPostCode()).isEqualTo(postCode);
        assertThat(result.getVatNumber()).isEqualTo(vatNumber);
        assertThat(result.getBank()).isEqualTo(bank);
    }

}