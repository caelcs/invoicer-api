package uk.co.caeldev.invoicer.api.features.companies;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import uk.co.caeldev.invoicer.api.features.common.domain.Bank;

public class CompanyRequest {

    private String name;
    private String address;
    private String postCode;
    private String vatNumber;
    private Bank bank;

    public CompanyRequest() {
    }

    public CompanyRequest(final String name,
                          final String address,
                          final String postCode,
                          final String vatNumber,
                          final Bank bank) {
        this.name = name;
        this.address = address;
        this.postCode = postCode;
        this.vatNumber = vatNumber;
        this.bank = bank;
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
