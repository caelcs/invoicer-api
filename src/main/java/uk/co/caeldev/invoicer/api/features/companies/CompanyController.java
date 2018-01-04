package uk.co.caeldev.invoicer.api.features.companies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(final CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(value = "/companies",
            produces = {APPLICATION_JSON_VALUE},
            consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<CompanyResource> create(final @RequestBody CompanyRequest companyRequest) {

        final Company company = companyService.create(companyRequest.getName(), companyRequest.getAddress(),
                companyRequest.getBank(), companyRequest.getPostCode(), companyRequest.getVatNumber());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CompanyResourceBuilder
                        .newBuilder()
                        .withCompany(company)
                        .build());
    }
}
