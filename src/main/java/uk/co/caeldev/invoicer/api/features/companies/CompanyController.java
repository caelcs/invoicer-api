package uk.co.caeldev.invoicer.api.features.companies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.caeldev.invoicer.api.features.common.exception.ObjectNotFoundException;

import javax.websocket.server.PathParam;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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

        final Company companySaved = companyService.create(companyRequest.getName(), companyRequest.getAddress(),
                companyRequest.getBank(), companyRequest.getPostCode(), companyRequest.getVatNumber());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CompanyResourceBuilder
                        .newBuilder()
                        .withCompany(companySaved)
                        .build());
    }

    @PostMapping(value = "/companies/{companyGuid}",
            produces = {APPLICATION_JSON_VALUE},
            consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<CompanyResource> update(final @RequestBody CompanyRequest companyRequest,
                                                  final @PathVariable UUID companyGuid) {
        final Company companyUpdated = companyService.update(companyGuid, companyRequest.getName(), companyRequest.getAddress(),
                companyRequest.getBank(), companyRequest.getPostCode(), companyRequest.getVatNumber());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CompanyResourceBuilder
                        .newBuilder()
                        .withCompany(companyUpdated)
                        .build());
    }

    @GetMapping(value = "/companies/{companyGuid}",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<CompanyResource> get(final @PathVariable UUID companyGuid) {
        final Company latestByGuid = companyService.findLatestByGuid(companyGuid);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CompanyResourceBuilder
                        .newBuilder()
                        .withCompany(latestByGuid)
                        .build());
    }

    @DeleteMapping("/companies/{companyGuid}")
    public ResponseEntity delete(final @PathVariable("companyGuid") UUID companyGuid) {
        companyService.delete(companyGuid);
        return ResponseEntity.noContent().build();
    }
}
