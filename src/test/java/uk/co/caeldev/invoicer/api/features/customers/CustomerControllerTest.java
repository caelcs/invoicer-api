package uk.co.caeldev.invoicer.api.features.customers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

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

}
