package uk.co.caeldev.invoicer.api.features.customers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import uk.co.caeldev.invoicer.api.features.common.exception.ObjectNotFoundException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Mock
    private CustomerService customerService;

    private CustomerController customerController;

    @Before
    public void setUp() {
        customerController = new CustomerController(customerService);
    }

    @Test
    public void shouldCreateCustomer() {
        //Given
        final Customer customer = TestCustomerBuilder.newBuilder().build();
        final CustomerRequest customerRequest = TestCustomerRequestBuilder.newBuilder().build();

        //And
        when(customerService.create(customerRequest.getName(), customerRequest.getAddress(),
                customerRequest.getPostCode(), customerRequest.getVatNumber()))
                .thenReturn(customer);

        //When
        final ResponseEntity<CustomerResource> response = customerController.create(customerRequest);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(CREATED);
    }

    @Test
    public void shouldUpdateCustomer() {
        //Given
        final CustomerRequest customerRequest = TestCustomerRequestBuilder.newBuilder().build();
        final UUID customerGuid = UUID.randomUUID();

        //And
        final Customer expectedCustomer = TestCustomerBuilder.newBuilder()
                .withAddress(customerRequest.getAddress())
                .withGuid(customerGuid)
                .withName(customerRequest.getName())
                .withPostCode(customerRequest.getPostCode())
                .withVatNumber(customerRequest.getVatNumber())
                .build();
        when(customerService.update(customerGuid, customerRequest.getName(), customerRequest.getAddress(),
                customerRequest.getPostCode(), customerRequest.getVatNumber()))
                .thenReturn(expectedCustomer);

        //When
        final ResponseEntity<CustomerResource> response = customerController.update(customerGuid, customerRequest);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(OK);
        final CustomerResource body = response.getBody();
        assertThat(body.getGuid()).isEqualTo(customerGuid);
        assertThat(body.getName()).isEqualTo(expectedCustomer.getName());
        assertThat(body.getAddress()).isEqualTo(expectedCustomer.getAddress());
        assertThat(body.getPostCode()).isEqualTo(expectedCustomer.getPostCode());
        assertThat(body.getVatNumber()).isEqualTo(expectedCustomer.getVatNumber());
    }

    @Test
    public void shouldThrowExceptionWhenCustomerGuidDoesNotExists() {
        //Given
        final CustomerRequest customerRequest = TestCustomerRequestBuilder.newBuilder().build();
        final UUID customerGuid = UUID.randomUUID();

        //And
        when(customerService.update(customerGuid, customerRequest.getName(), customerRequest.getAddress(),
                customerRequest.getPostCode(), customerRequest.getVatNumber()))
                .thenThrow(ObjectNotFoundException.class);

        //Expect
        expectedException.expect(ObjectNotFoundException.class);

        //When
        customerController.update(customerGuid, customerRequest);
    }

}
