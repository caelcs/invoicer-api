package uk.co.caeldev.invoicer.api.features.companies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.caeldev.invoicer.api.BaseIntegrationTest;
import uk.co.caeldev.invoicer.api.features.common.exception.ApiError;
import uk.co.caeldev.invoicer.api.features.common.exception.ErrorCode;
import uk.co.caeldev.invoicer.api.features.customers.*;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.http.HttpStatus.*;

@RunWith(SpringRunner.class)
public class CustomerApiTest extends BaseIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

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

    @Test
    public void shouldUpdateCustomer() {
        //Given
        final CustomerRequest customerRequest = TestCustomerRequestBuilder.newBuilder().build();

        //And
        final Customer customer = TestCustomerBuilder.newBuilder().build();
        customerRepository.save(customer);

        //When
        final CustomerResource result = given()
                .port(serverPort)
                .headers("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .pathParam("customerGuid", customer.getGuid())
                .body(customerRequest)
                .when()
                .post("/customers/{customerGuid}")
                .then()
                .assertThat()
                .statusCode(equalTo(OK.value())).extract().body().as(CustomerResource.class);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getGuid()).isNotNull();
        assertThat(result.getName()).isEqualTo(customerRequest.getName());
        assertThat(result.getAddress()).isEqualTo(customerRequest.getAddress());
        assertThat(result.getPostCode()).isEqualTo(customerRequest.getPostCode());
        assertThat(result.getVatNumber()).isEqualTo(customerRequest.getVatNumber());
    }

    @Test
    public void shouldUpdateFailWhenCustomerDoesNotExists() {
        //Given
        final CustomerRequest customerRequest = TestCustomerRequestBuilder.newBuilder().build();

        //When
        final ApiError result = given()
                .port(serverPort)
                .headers("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .pathParam("customerGuid", UUID.randomUUID())
                .body(customerRequest)
                .when()
                .post("/customers/{customerGuid}")
                .then()
                .assertThat()
                .statusCode(equalTo(NOT_FOUND.value())).extract().body().as(ApiError.class);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(ErrorCode.NOT_FOUND.getCode());
        assertThat(result.getMessage()).isEqualTo(ErrorCode.NOT_FOUND.getMessage());
    }
}
