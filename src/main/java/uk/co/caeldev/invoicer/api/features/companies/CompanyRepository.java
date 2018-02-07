package uk.co.caeldev.invoicer.api.features.companies;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import uk.co.caeldev.spring.moprhia.repository.GenericMorphiaRepository;

import java.util.Optional;
import java.util.UUID;

public class CompanyRepository extends GenericMorphiaRepository<Company, ObjectId> {

    public CompanyRepository(final Datastore ds) {
        super(Company.class, ds);
    }

    public Optional<Company> findLatestByGuid(final UUID companyGuid) {
        Query<Company> query = getDatastore().createQuery(Company.class);
        return Optional.ofNullable(query.field("guid").equal(companyGuid).order("-version").get());
    }
}
