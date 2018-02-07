package uk.co.caeldev.invoicer.api.features.customers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
