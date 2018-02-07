package uk.co.caeldev.invoicer.api.features.companies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.caeldev.invoicer.api.BaseIntegrationTest;
import uk.co.caeldev.invoicer.api.features.customers.CustomerRequest;
import uk.co.caeldev.invoicer.api.features.customers.CustomerResource;
import uk.co.caeldev.invoicer.api.features.customers.TestCustomerRequestBuilder;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.http.HttpStatus.CREATED;

@RunWith(SpringRunner.class)
public class CustomerApiTest extends BaseIntegrationTest {
    
    @Test
    public void shouldCreateCustomer() {
        //Given
        final CustomerRequest customerRequest = TestCustomerRequestBuilder.newBuilder().build();

        //When
        final CustomerResource result = given()
                .port(serverPort)
                .headers("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(customerRequest)
                .when()
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(equalTo(CREATED.value())).extract().body().as(CustomerResource.class);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getGuid()).isNotNull();
        assertThat(result.getName()).isEqualTo(customerRequest.getName());
        assertThat(result.getAddress()).isEqualTo(customerRequest.getAddress());
        assertThat(result.getPostCode()).isEqualTo(customerRequest.getPostCode());
        assertThat(result.getVatNumber()).isEqualTo(customerRequest.getVatNumber());
    }
}
