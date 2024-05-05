package com.collectionpoints.jpa;

import com.collectionpoints.jpa.entity.CollectionPointEntity;
import com.collectionpoints.jpa.helper.AdapterOperations;
import com.collectionpoints.model.CollectionPoint;
import com.collectionpoints.model.dto.CollectionPointFilter;
import com.collectionpoints.model.dto.CollectionPointRequest;
import com.collectionpoints.model.gateways.CollectionPointRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JPARepositoryAdapter extends AdapterOperations<CollectionPoint, CollectionPointEntity, Integer, JPARepository>
implements CollectionPointRepository
{

    public JPARepositoryAdapter(JPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, CollectionPoint.class));
    }

    @Override
    public List<CollectionPoint> getAll(CollectionPointFilter collectionPointFilter) {
        List<CollectionPoint> result = new ArrayList<>();
        this.repository.findByFilters(
                collectionPointFilter.getSearch(),
                collectionPointFilter.getStatusId()
            )
            .forEach(collectionPointEntity -> result.add(toEntity(collectionPointEntity)));
        return result;
    }

    @Override
    public CollectionPoint getById(int id) {
        return this.findById(id);
    }

    @Override
    public CollectionPoint create(CollectionPoint collectionPoint) {
        return this.save(this.mapper.map(collectionPoint, CollectionPoint.class));
    }
}
