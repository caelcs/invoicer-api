package uk.co.caeldev.invoicer.api.features.companies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.caeldev.invoicer.api.BaseIntegrationTest;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static uk.org.fyodor.generators.RDG.string;

@RunWith(SpringRunner.class)
public class CompanyApiTest extends BaseIntegrationTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void testee() {
        companyRepository.deleteAll();
    }
    
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

    @Test
    public void shouldGetLatestCompanyVersion() {
        //Given
        final Company company = TestCompanyBuilder.newBuilder().build();
        final Company savedCompany = companyRepository.save(company);
        savedCompany.setName(string().next());
        final Company company1 = companyRepository.save(savedCompany);

        //And
        final CompanyRequest companyRequest = TestCompanyRequestBuilder.newBuilder().build();

        //Then
        final CompanyResource result = given()
                .port(serverPort)
                .headers("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .pathParam("companyGuid", savedCompany.getGuid())
                .body(companyRequest)
                .when()
                .get("/companies/{companyGuid}")
                .then()
                .assertThat()
                .statusCode(equalTo(OK.value())).extract().body().as(CompanyResource.class);

        assertThat(result.getGuid()).isNotNull();
        assertThat(result.getAddress()).isEqualTo(company1.getAddress());
        assertThat(result.getName()).isEqualTo(company1.getName());
        assertThat(result.getBank()).isEqualTo(company1.getBank());
        assertThat(result.getPostCode()).isEqualTo(company1.getPostCode());
        assertThat(result.getVatNumber()).isEqualTo(company1.getVatNumber());

        //And
        final Optional<Company> latestCompany = companyRepository.findLatestByGuid(savedCompany.getGuid());
        assertThat(latestCompany.get().getVersion()).isEqualTo(2);
    }
}
