package uk.co.caeldev.invoicer.api.features.customers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.caeldev.invoicer.api.features.common.exception.ObjectNotFoundException;
import uk.co.caeldev.invoicer.api.features.common.utils.EntityMerger;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static uk.org.fyodor.generators.RDG.postcode;
import static uk.org.fyodor.generators.RDG.string;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerFactory customerFactory;

    @Mock
    private EntityMerger entityMerger;

    private CustomerService customerService;

    @Before
    public void testee() {
        customerService = new CustomerService(customerRepository, customerFactory, entityMerger);
    }

    @Test
    public void shouldCreateCustomer() {
        //Given
        final String name = string().next();
        final String address = string().next();
        final String postCode = postcode().next();
        final String vatNumber = string().next();

        //And
        final Customer expectedCustomer = TestCustomerBuilder.newBuilder().build();
        when(customerFactory.getInstance(name, address, postCode, vatNumber))
                .thenReturn(expectedCustomer);

        //And
        when(customerRepository.save(expectedCustomer))
                .thenReturn(expectedCustomer);

        //When
        final Customer customer = customerService.create(name, address, postCode, vatNumber);

        //Then
        assertThat(customer).isNotNull();
    }

    @Test
    public void shouldUpdateCustomer() {
        //Given
        final UUID customerGuid = UUID.randomUUID();
        final String name = string().next();
        final String address = string().next();
        final String postCode = postcode().next();
        final String vatNumber = string().next();

        //And
        final Customer expectedCustomer = TestCustomerBuilder.newBuilder().build();
        when(customerRepository.findLatestByGuid(customerGuid))
                .thenReturn(Optional.of(expectedCustomer));

        //And
        final Customer expectedCustomerToBeMerged = TestCustomerBuilder.newBuilder().build();
        when(customerFactory.getInstance(name, address, postCode, vatNumber))
                .thenReturn(expectedCustomerToBeMerged);

        //And
        final Customer expectedCustomerMerged = TestCustomerBuilder.newBuilder().build();
        when(entityMerger.merge(expectedCustomerToBeMerged, expectedCustomer))
                .thenReturn(expectedCustomerMerged);

        //And
        when(customerRepository.save(expectedCustomerMerged))
                .thenReturn(expectedCustomerMerged);

        //When
        final Customer customer = customerService.update(customerGuid, name, address, postCode, vatNumber);

        //Then
        assertThat(customer).isNotNull();
        assertThat(customer).isEqualTo(expectedCustomerMerged);
    }

    @Test
    public void shouldThrowExceptionWhenCustomerDoesNotExists() {
        //Given
        final UUID customerGuid = UUID.randomUUID();
        final String name = string().next();
        final String address = string().next();
        final String postCode = postcode().next();
        final String vatNumber = string().next();

        //And
        when(customerRepository.findLatestByGuid(customerGuid))
                .thenReturn(Optional.empty());

        //Expect
        expectedException.expect(ObjectNotFoundException.class);

        //When
        customerService.update(customerGuid, name, address, postCode, vatNumber);

    }

}