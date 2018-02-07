package uk.co.caeldev.invoicer.api.features.customers;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import uk.co.caeldev.spring.moprhia.repository.GenericMorphiaRepository;

public class CustomerRepository extends GenericMorphiaRepository<Customer, ObjectId> {

    public CustomerRepository(final Datastore datastore) {
        super(Customer.class, datastore);
    }
}
