package uk.co.caeldev.invoicer.api.features.common.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Bank {

    private String name;
    private String accountNumber;
    private String sortCode;

    public Bank() {
    }

    public Bank(final String name,
                final String accountNumber,
                final String sortCode) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getSortCode() {
        return sortCode;
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
