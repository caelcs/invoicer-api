package uk.co.caeldev.invoicer.api.features.customers;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.bson.types.ObjectId;
import uk.co.caeldev.invoicer.api.features.common.domain.BaseEntity;

import java.util.UUID;

public class Customer extends BaseEntity {

    private String name;
    private String address;
    private String postCode;
    private String vatNumber;

    public Customer(final String name,
                    final String address,
                    final String postCode,
                    final String vatNumber) {
        this.name = name;
        this.address = address;
        this.postCode = postCode;
        this.vatNumber = vatNumber;
    }

    private Customer(final Builder builder) {
        setId(builder.id);
        setGuid(builder.guid);
        version = builder.version;
        setName(builder.name);
        setAddress(builder.address);
        setPostCode(builder.postCode);
        setVatNumber(builder.vatNumber);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(final Customer customer) {
        Builder builder = new Builder();
        builder.id = customer.getId();
        builder.guid = customer.getGuid();
        builder.version = customer.getVersion();
        builder.name = customer.getName();
        builder.address = customer.getAddress();
        builder.postCode = customer.getPostCode();
        builder.vatNumber = customer.getVatNumber();
        return builder;
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

    public static class Builder {
        private ObjectId id;
        private UUID guid;
        private Long version;
        private String name;
        private String address;
        private String postCode;
        private String vatNumber;

        protected Builder() {
        }

        public Builder withId(final ObjectId id) {
            this.id = id;
            return this;
        }

        public Builder withGuid(final UUID guid) {
            this.guid = guid;
            return this;
        }

        public Builder withVersion(final Long version) {
            this.version = version;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withAddress(final String address) {
            this.address = address;
            return this;
        }

        public Builder withPostCode(final String postCode) {
            this.postCode = postCode;
            return this;
        }

        public Builder withVatNumber(final String vatNumber) {
            this.vatNumber = vatNumber;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
