package uk.co.caeldev.invoicer.api.features.common.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Version;

import java.util.UUID;

public class BaseEntity {

    @Id
    protected ObjectId id;

    protected UUID guid;

    @Version
    protected Long version;

    public BaseEntity() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public Long getVersion() {
        return version;
    }
}
