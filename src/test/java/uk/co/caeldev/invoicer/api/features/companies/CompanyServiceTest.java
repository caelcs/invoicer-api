package uk.co.caeldev.invoicer.api.features.companies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import uk.co.caeldev.invoicer.api.features.common.domain.Bank;

import java.util.Optional;
import java.util.UUID;

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

    @Mock
    private CompanyMerger companyMerger;

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

    @Test
    public void shouldUpdateCompanyName() {
        //Given
        final UUID companyGuid = UUID.randomUUID();
        final String name = string().next();
        final String address = string().next();
        final Bank bank = TestBankBuilder.newBuilder().build();
        final String postCode = postcode().next();
        final String vatNumber = string().next();

        //And
        final Company expectedExistingCompany = TestCompanyBuilder.newBuilder()
                .withAddress(address)
                .withBank(bank)
                .withGuid(companyGuid)
                .withPostCode(postCode)
                .withVatNumber(vatNumber)
                .build();
        when(companyRepository.findByGuid(companyGuid))
                .thenReturn(Optional.of(expectedExistingCompany));

        //And
        final Company companyToBeUpdate = TestCompanyBuilder.newBuilder()
                .withAddress(address)
                .withName(name)
                .withBank(bank)
                .withGuid(companyGuid)
                .withPostCode(postCode)
                .withVatNumber(vatNumber)
                .build();



        when(companyFactory.getInstance(name, address, bank, postCode, vatNumber))
                .thenReturn(companyToBeUpdate);

        //And
        when(companyRepository.save(companyToBeUpdate))
                .thenReturn(companyToBeUpdate);

        //When
        final Company result = companyService.update(companyGuid, name, address, bank, postCode, vatNumber);

        //Then
        assertThat(result)
                .isEqualToComparingFieldByField(companyToBeUpdate);
    }

}