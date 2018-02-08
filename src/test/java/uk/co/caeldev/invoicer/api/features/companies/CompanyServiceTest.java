package uk.co.caeldev.invoicer.api.features.companies;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.caeldev.invoicer.api.features.common.domain.Bank;
import uk.co.caeldev.invoicer.api.features.common.exception.ObjectNotFoundException;
import uk.co.caeldev.invoicer.api.features.common.utils.EntityMerger;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uk.org.fyodor.generators.RDG.postcode;
import static uk.org.fyodor.generators.RDG.string;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyFactory companyFactory;

    @Mock
    private EntityMerger entityMerger;

    private CompanyService companyService;

    @Before
    public void testee() {
        companyService = new CompanyService(companyRepository, companyFactory, entityMerger);
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
        when(companyRepository.findLatestByGuid(companyGuid))
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
        final Company expectedMergedCompany = TestCompanyBuilder.newBuilder().build();
        when(entityMerger.merge(companyToBeUpdate, expectedExistingCompany))
                .thenReturn(expectedMergedCompany);

        //And
        final Company expectedCompanyUpdate = TestCompanyBuilder.newBuilder().build();
        when(companyRepository.save(expectedMergedCompany))
                .thenReturn(expectedCompanyUpdate);

        //When
        final Company result = companyService.update(companyGuid, name,
                address, bank, postCode, vatNumber);

        //Then
        assertThat(result)
                .isEqualToComparingFieldByField(expectedCompanyUpdate);
    }

    @Test
    public void shouldUpdateCompanyThrowExceptionWhenCompanyDoesNotExists() {
        //Given
        final UUID companyGuid = UUID.randomUUID();
        final String name = string().next();
        final String address = string().next();
        final Bank bank = TestBankBuilder.newBuilder().build();
        final String postCode = postcode().next();
        final String vatNumber = string().next();

        //And
        when(companyRepository.findLatestByGuid(companyGuid))
                .thenReturn(Optional.empty());

        //Expect
        expectedException.expect(ObjectNotFoundException.class);

        //When
        companyService.update(companyGuid, name, address, bank, postCode, vatNumber);
    }

    @Test
    public void shouldGetLatestCompany() {
        //Given
        final UUID companyGuid = UUID.randomUUID();

        //And
        final Company expectedCompany = TestCompanyBuilder.newBuilder().build();
        when(companyRepository.findLatestByGuid(companyGuid))
                .thenReturn(Optional.of(expectedCompany));

        //When
        final Company latestByGuid = companyService.findLatestByGuid(companyGuid);

        //Then
        assertThat(latestByGuid).isEqualTo(expectedCompany);
    }

    @Test
    public void shouldFindLatestByGuidThrowExceptionWhenCompanyDoesNotExists() {
        //Given
        final UUID companyGuid = UUID.randomUUID();

        //And
        when(companyRepository.findLatestByGuid(companyGuid))
                .thenReturn(Optional.empty());

        //Expect
        expectedException.expect(ObjectNotFoundException.class);

        //When
        companyService.findLatestByGuid(companyGuid);

    }

    @Test
    public void shouldDeleteCompany() {
        //Given
        final UUID companyGuid = UUID.randomUUID();

        //And
        final Company expectedCompany = TestCompanyBuilder.newBuilder().build();
        when(companyRepository.findLatestByGuid(companyGuid))
                .thenReturn(Optional.of(expectedCompany));

        //When
        companyService.delete(companyGuid);

        //Then
        verify(companyRepository).delete(expectedCompany);

    }

    @Test
    public void shouldThrowExceptionWhenCompanyDoesNotExists() {
        //Given
        final UUID companyGuid = UUID.randomUUID();

        //And
        when(companyRepository.findLatestByGuid(companyGuid))
                .thenReturn(Optional.empty());

        //Expect
        expectedException.expect(ObjectNotFoundException.class);

        //When
        companyService.delete(companyGuid);
    }

}