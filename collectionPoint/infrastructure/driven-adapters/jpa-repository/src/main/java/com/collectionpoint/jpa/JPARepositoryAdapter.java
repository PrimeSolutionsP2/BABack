package com.collectionpoint.jpa;

import com.collectionpoint.jpa.entity.CollectionPointEntity;
import com.collectionpoint.jpa.helper.AdapterOperations;
import com.collectionpoint.model.CollectionPoint;
import com.collectionpoint.model.CollectionPointFilter;
import com.collectionpoint.model.gateways.CollectionPointRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JPARepositoryAdapter extends AdapterOperations<CollectionPoint, CollectionPointEntity, Integer, JPARepository>
implements CollectionPointRepository
{

    public JPARepositoryAdapter(JPARepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, CollectionPoint.class));
    }

    @Override
    public List<CollectionPoint> getAll(CollectionPointFilter cpf) {
        List<CollectionPoint> result = new ArrayList<>();
        this.repository.findByFilters(
                cpf.getUserId(),
                cpf.getName(),
                cpf.getEmail(),
                cpf.getAddress(),
                cpf.getCity(),
                cpf.getState(),
                cpf.getCountry(),
                cpf.getStatus()
            )
            .forEach(collectionPointEntity -> result.add(toEntity(collectionPointEntity)));
        return result;
    }

    @Override
    public CollectionPoint getById(int id) {
        return this.findById(id);
    }

}
