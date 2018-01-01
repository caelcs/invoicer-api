package uk.co.caeldev.invoicer.api.features.companies;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.UUID;

public class CompanyResource extends ResourceSupport {

    private UUID guid;
    private String name;
    private String address;
    private String postCode;
    private String vatNumber;
    private Bank bank;

    @JsonCreator
    public CompanyResource(final @JsonProperty("guid") UUID guid,
                           final @JsonProperty("name") String name,
                           final @JsonProperty("address") String address,
                           final @JsonProperty("postCode") String postCode,
                           final @JsonProperty("vatNumber") String vatNumber,
                           final @JsonProperty("bank") Bank bank) {
        this.guid = guid;
        this.name = name;
        this.address = address;
        this.postCode = postCode;
        this.vatNumber = vatNumber;
        this.bank = bank;
    }

    public UUID getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public Bank getBank() {
        return bank;
    }
}
