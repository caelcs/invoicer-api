package uk.co.caeldev.invoicer.api.features.companies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.org.fyodor.generators.RDG.postcode;
import static uk.org.fyodor.generators.RDG.string;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    private CompanyService companyService;

    @Before
    public void testee() throws Exception {
        companyService = new CompanyService(companyRepository);
    }

    @Test
    public void shouldPersistCompany() throws Exception {
        //Given
        final String name = string().next();
        final String address = string().next();
        final Bank bank = TestBankBuilder.newBuilder().build();
        final String postCode = postcode().next();
        final String vatNumber = string().next();

        //When
        final Company result = this.companyService.create(name, address, bank, postCode, vatNumber);

        //Then
        assertThat(result).isNotNull();
    }

}