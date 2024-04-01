package com.collectionpoint.usecase;

import com.collectionpoint.model.CollectionPoint;
import com.collectionpoint.model.dto.CollectionPointFilter;
import com.collectionpoint.model.dto.CollectionPointRequest;
import com.collectionpoint.model.gateways.CollectionPointRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CollectionPointUseCase {
    private CollectionPointRepository collectionPointRepository;

    public List<CollectionPoint> getAll(CollectionPointFilter collectionPointFilter) {
        return collectionPointRepository.getAll(collectionPointFilter);
    }

    public CollectionPoint getById(int id) {
        return collectionPointRepository.getById(id);
    }

    public CollectionPoint requestCollectionPoint(CollectionPointRequest collectionPointRequest) {
        // TODO
        // I must request user api in order to know if a user with that the given info already exists
        // If doesn't then gotta request user api to create insert a user in db with the info that was given to me

        return collectionPointRepository.create(collectionPointRequest);
    }

}
