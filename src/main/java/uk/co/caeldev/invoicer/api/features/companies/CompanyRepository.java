package uk.co.caeldev.invoicer.api.features.companies;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import uk.co.caeldev.spring.moprhia.repository.GenericMorphiaRepository;

import java.util.Optional;
import java.util.UUID;

public class CompanyRepository extends GenericMorphiaRepository<Company, ObjectId> {

    public CompanyRepository(final Datastore ds) {
        super(Company.class, ds);
    }

    public Optional<Company> findByGuid(final UUID companyGuid) {
        return null;
    }
}
