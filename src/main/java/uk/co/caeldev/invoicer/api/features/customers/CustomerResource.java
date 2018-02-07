package uk.co.caeldev.invoicer.api.features.customers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.UUID;

public class CustomerResource {

    private UUID guid;
    private String name;
    private String address;
    private String postCode;
    private String vatNumber;

    @JsonCreator
    public CustomerResource(final @JsonProperty("guid") UUID guid,
                            final @JsonProperty("name") String name,
                            final @JsonProperty("address") String address,
                            final @JsonProperty("postCode") String postCode,
                            final @JsonProperty("vatNumber") String vatNumber) {
        this.guid = guid;
        this.name = name;
        this.address = address;
        this.postCode = postCode;
        this.vatNumber = vatNumber;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(final UUID guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(final String postCode) {
        this.postCode = postCode;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(final String vatNumber) {
        this.vatNumber = vatNumber;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
