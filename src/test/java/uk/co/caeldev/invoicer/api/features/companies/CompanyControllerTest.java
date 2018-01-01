package uk.co.caeldev.invoicer.api.features.companies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompanyControllerTest {

    @Mock
    private CompanyService companyService;

    private CompanyController companyController;

    @Before
    public void testee() throws Exception {
        companyController = new CompanyController(companyService);
    }
    
    @Test
    public void shouldCreateCompany() throws Exception {
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
}