package uk.co.caeldev.invoicer.api.features.customers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static uk.org.fyodor.generators.RDG.postcode;
import static uk.org.fyodor.generators.RDG.string;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerFactory customerFactory;

    private CustomerService customerService;

    @Before
    public void testee() {
        customerService = new CustomerService(customerRepository, customerFactory);
    }

    @Test
    public void shouldCreateCustomer() {
        //Given
        final String name = string().next();
        final String address = string().next();
        final String postCode = postcode().next();
        final String vatNumber = string().next();

        //And
        final Customer expectedCompany = TestCustomerBuilder.newBuilder().build();
        when(customerFactory.getInstance(name, address, postCode, vatNumber))
                .thenReturn(expectedCompany);

        //And
        when(customerRepository.save(expectedCompany))
                .thenReturn(expectedCompany);

        //When
        final Customer customer = customerService.create(name, address, postCode, vatNumber);

        //Then
        assertThat(customer).isNotNull();
    }

}