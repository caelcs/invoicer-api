package uk.co.caeldev.invoicer.api.features.companies;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Version;

import java.util.UUID;

@Entity("companies")
public class Company {

    @Id
    private ObjectId id;
    private UUID guid;
    private String username;
    private String name;
    private String address;
    private String postCode;
    private Bank bank;
    private String vatNumber;
    @Version
    private Long version;

    public Company(final UUID guid,
                   final String username,
                   final String name,
                   final String address,
                   final String postCode,
                   final Bank bank,
                   final String vatNumber) {
        this.guid = guid;
        this.username = username;
        this.name = name;
        this.address = address;
        this.postCode = postCode;
        this.bank = bank;
        this.vatNumber = vatNumber;
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

    public Bank getBank() {
        return bank;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public UUID getGuid() {
        return guid;
    }

    public ObjectId getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public String getUsername() {
        return username;
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
