package uk.co.caeldev.invoicer.api.features.companies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.caeldev.invoicer.api.features.common.domain.Bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static uk.org.fyodor.generators.RDG.postcode;
import static uk.org.fyodor.generators.RDG.string;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyFactory companyFactory;

    private CompanyService companyService;

    @Before
    public void testee() {
        companyService = new CompanyService(companyRepository, companyFactory);
    }

    @Test
    public void shouldPersistCompany() {
        //Given
        final String name = string().next();
        final String address = string().next();
        final Bank bank = TestBankBuilder.newBuilder().build();
        final String postCode = postcode().next();
        final String vatNumber = string().next();

        //And
        final Company expectedCompany = TestCompanyBuilder.newBuilder().build();
        when(companyFactory.getInstance(name, address, bank, postCode, vatNumber))
                .thenReturn(expectedCompany);

        //And
        final Company expectedCompanySaved = TestCompanyBuilder.newBuilder().build();
        when(companyRepository.save(expectedCompany))
                .thenReturn(expectedCompanySaved);
        //When
        final Company result = this.companyService.create(name, address, bank, postCode, vatNumber);

        //Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedCompanySaved);
    }

}