package uk.co.caeldev.invoicer.api.features.companies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.caeldev.invoicer.api.BaseIntegrationTest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.http.HttpStatus.CREATED;

@RunWith(SpringRunner.class)
public class CompanyApiTest extends BaseIntegrationTest {
    
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
}
