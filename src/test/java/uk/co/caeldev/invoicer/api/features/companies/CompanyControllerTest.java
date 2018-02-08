package uk.co.caeldev.invoicer.api.features.companies;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.caeldev.invoicer.api.features.common.Generators;
import uk.co.caeldev.invoicer.api.features.common.exception.ServiceException;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static uk.org.fyodor.generators.RDG.list;

@RunWith(MockitoJUnitRunner.class)
public class CompanyControllerTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private CompanyService companyService;

    private CompanyController companyController;

    @Before
    public void testee() {
        companyController = new CompanyController(companyService);
    }
    
    @Test
    public void shouldCreateCompany() {
        //Given
        final CompanyRequest companyRequest = TestCompanyRequestBuilder.newBuilder().build();

        //And
        final Company expectedCompany = TestCompanyBuilder.newBuilder().build();
        when(companyService.create(companyRequest.getName(), companyRequest.getAddress(),
                companyRequest.getBank(), companyRequest.getPostCode(), companyRequest.getVatNumber()))
                .thenReturn(expectedCompany);

        //When
        final ResponseEntity<CompanyResource> result = this.companyController.create(companyRequest);
         
        //Then
        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        final CompanyResource body = result.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getName()).isEqualTo(expectedCompany.getName());
        assertThat(body.getAddress()).isEqualTo(expectedCompany.getAddress());
        assertThat(body.getPostCode()).isEqualTo(expectedCompany.getPostCode());
        assertThat(body.getBank()).isEqualTo(expectedCompany.getBank());
        assertThat(body.getVatNumber()).isEqualTo(expectedCompany.getVatNumber());
    }

    @Test
    public void shouldThrowServiceExceptionWhenCreationFails() {
        //Given
        final CompanyRequest companyRequest = TestCompanyRequestBuilder.newBuilder().build();

        //And
        when(companyService.create(companyRequest.getName(), companyRequest.getAddress(),
                companyRequest.getBank(), companyRequest.getPostCode(), companyRequest.getVatNumber()))
                .thenThrow(ServiceException.class);

        //Expect
        exception.expect(ServiceException.class);

        //When
        this.companyController.create(companyRequest);
    }

    @Test
    public void shouldUpdateCompany() {
        //Given
        final CompanyRequest companyRequest = TestCompanyRequestBuilder.newBuilder().build();
        final UUID companyGuid = UUID.randomUUID();

        //And
        final Company expectedCompany = TestCompanyBuilder.newBuilder().build();
        when(companyService.update(companyGuid, companyRequest.getName(), companyRequest.getAddress(),
                companyRequest.getBank(), companyRequest.getPostCode(), companyRequest.getVatNumber()))
                .thenReturn(expectedCompany);

        //When
        final ResponseEntity<CompanyResource> result = this.companyController
                .update(companyRequest, companyGuid);

        //Then
        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        final CompanyResource body = result.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getGuid()).isEqualTo(expectedCompany.getGuid());
        assertThat(body.getName()).isEqualTo(expectedCompany.getName());
        assertThat(body.getAddress()).isEqualTo(expectedCompany.getAddress());
        assertThat(body.getPostCode()).isEqualTo(expectedCompany.getPostCode());
        assertThat(body.getBank()).isEqualTo(expectedCompany.getBank());
        assertThat(body.getVatNumber()).isEqualTo(expectedCompany.getVatNumber());
    }

    @Test
    public void shouldGetLatestCompany() {
        //Given
        final UUID companyGuid = UUID.randomUUID();

        //And
        final Company expectedCompany = TestCompanyBuilder.newBuilder().build();
        when(companyService.findLatestByGuid(companyGuid))
                .thenReturn(expectedCompany);

        //When
        final ResponseEntity<CompanyResource> result = this.companyController.get(companyGuid);

        //Then
        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        final CompanyResource body = result.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getGuid()).isEqualTo(expectedCompany.getGuid());
        assertThat(body.getName()).isEqualTo(expectedCompany.getName());
        assertThat(body.getAddress()).isEqualTo(expectedCompany.getAddress());
        assertThat(body.getPostCode()).isEqualTo(expectedCompany.getPostCode());
        assertThat(body.getBank()).isEqualTo(expectedCompany.getBank());
        assertThat(body.getVatNumber()).isEqualTo(expectedCompany.getVatNumber());
    }

    @Test
    public void shouldDeleteByGuidWhenCompanyExists() {
        //Given
        final UUID companyGuid = UUID.randomUUID();

        //When
        final ResponseEntity result = companyController.delete(companyGuid);

        //Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void shouldGetAllCompanies() {
        //Given
        final List<Company> expectedCompanies = list(Generators.ofCompany()).next();
        when(companyService.findAll()).thenReturn(expectedCompanies);

        //When
        final ResponseEntity<List<CompanyResource>> result = companyController.getAll();

        //Then
        final List<CompanyResource> body = result.getBody();
        assertThat(body).isNotNull();
        assertThat(body).hasSameSizeAs(expectedCompanies);
    }

}