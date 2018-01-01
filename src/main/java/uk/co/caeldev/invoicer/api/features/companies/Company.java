package uk.co.caeldev.invoicer.api.features.companies;

import org.bson.types.ObjectId;

import java.util.UUID;

public class Company {

    private ObjectId id;
    private UUID guid;
    private String name;
    private String address;
    private String postCode;
    private Bank bank;
    private String vatNumber;

    public Company(final UUID guid,
                   final String name,
                   final String address,
                   final String postCode,
                   final Bank bank,
                   final String vatNumber) {
        this.guid = guid;
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
}
