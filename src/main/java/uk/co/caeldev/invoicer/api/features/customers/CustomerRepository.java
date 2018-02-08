package uk.co.caeldev.invoicer.api.features.customers;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import uk.co.caeldev.spring.moprhia.repository.GenericMorphiaRepository;

import java.util.Optional;
import java.util.UUID;

public class CustomerRepository extends GenericMorphiaRepository<Customer, ObjectId> {

    public CustomerRepository(final Datastore datastore) {
        super(Customer.class, datastore);
    }

    public Optional<Customer> findLatestByGuid(final UUID customerGuid) {
        Query<Customer> query = getDatastore().createQuery(Customer.class);
        return Optional.ofNullable(query.field("guid").equal(customerGuid).order("-version").get());
    }
}
