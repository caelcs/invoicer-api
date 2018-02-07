package uk.co.caeldev.invoicer.api.features.customers;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.org.fyodor.generators.RDG.postcode;
import static uk.org.fyodor.generators.RDG.string;

public class CustomerFactoryTest {

    private CustomerFactory customerFactory;
    
    @Before
    public void testee() {
        customerFactory = new CustomerFactory();
    }

    @Test
    public void shouldCreateCustomer() {
        //Given
        final String name = string().next();
        final String address = string().next();
        final String postCode = postcode().next();
        final String vatNumber = string().next();

        //When
        final Customer customer = customerFactory.getInstance(name, address, postCode, vatNumber);

        //Then
        assertThat(customer).isNotNull();
        assertThat(customer.getName()).isEqualTo(name);
        assertThat(customer.getAddress()).isEqualTo(address);
        assertThat(customer.getPostCode()).isEqualTo(postCode);
        assertThat(customer.getVatNumber()).isEqualTo(vatNumber);
    }
}