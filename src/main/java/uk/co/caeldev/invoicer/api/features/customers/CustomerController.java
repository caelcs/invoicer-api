package uk.co.caeldev.invoicer.api.features.customers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value="/customers",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerResource> create(final @RequestBody CustomerRequest customerRequest) {

        final Customer customer = customerService.create(customerRequest.getName(),
                customerRequest.getAddress(), customerRequest.getPostCode(), customerRequest.getVatNumber());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CustomerResourceBuilder
                        .newBuilder()
                        .withCustomer(customer)
                        .build());
    }

    @PostMapping(value="/customers/{customerId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerResource> update(final @PathVariable UUID customerGuid,
                                                   final @RequestBody CustomerRequest customerRequest) {

        final Customer customer = customerService.update(customerGuid, customerRequest.getName(),
                customerRequest.getAddress(), customerRequest.getPostCode(), customerRequest.getVatNumber());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CustomerResourceBuilder
                        .newBuilder()
                        .withCustomer(customer)
                        .build());
    }
}
