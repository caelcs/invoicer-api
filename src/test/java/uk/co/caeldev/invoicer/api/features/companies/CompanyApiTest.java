package uk.co.caeldev.invoicer.api.features.companies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.caeldev.invoicer.api.BaseIntegrationTest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
public class CompanyApiTest extends BaseIntegrationTest {

    @Autowired
    private CompanyRepository companyRepository;
    
    @Test
    public void shouldCreateCompany() {
        //Given
        final CompanyRequest companyRequest = TestCompanyRequestBuilder.newBuilder().build();

        //Then
        final CompanyResource result = given()
                .port(serverPort)
                .headers("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(companyRequest)
            .when()
                .post("/companies")
            .then()
                .assertThat()
                .statusCode(equalTo(CREATED.value())).extract().body().as(CompanyResource.class);

        assertThat(result.getGuid()).isNotNull();
        assertThat(result.getAddress()).isEqualTo(companyRequest.getAddress());
        assertThat(result.getName()).isEqualTo(companyRequest.getName());
        assertThat(result.getBank()).isEqualTo(companyRequest.getBank());
        assertThat(result.getPostCode()).isEqualTo(companyRequest.getPostCode());
        assertThat(result.getVatNumber()).isEqualTo(companyRequest.getVatNumber());
    }

    @Test
    public void shouldUpdateCompany() {
        //Given
        final Company company = TestCompanyBuilder.newBuilder().build();
        final Company savedCompany = companyRepository.save(company);

        //And
        final CompanyRequest companyRequest = TestCompanyRequestBuilder.newBuilder().build();

        //Then
        final CompanyResource result = given()
                .port(serverPort)
                .headers("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .pathParam("companyGuid", savedCompany.getGuid())
                .body(companyRequest)
                .when()
                .post("/companies/{companyGuid}")
                .then()
                .assertThat()
                .statusCode(equalTo(OK.value())).extract().body().as(CompanyResource.class);

        assertThat(result.getGuid()).isNotNull();
        assertThat(result.getAddress()).isEqualTo(companyRequest.getAddress());
        assertThat(result.getName()).isEqualTo(companyRequest.getName());
        assertThat(result.getBank()).isEqualTo(companyRequest.getBank());
        assertThat(result.getPostCode()).isEqualTo(companyRequest.getPostCode());
        assertThat(result.getVatNumber()).isEqualTo(companyRequest.getVatNumber());
    }
}
