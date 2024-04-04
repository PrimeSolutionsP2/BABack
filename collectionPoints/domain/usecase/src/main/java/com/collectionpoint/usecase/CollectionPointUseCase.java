package com.collectionpoint.usecase;

import com.collectionpoint.model.CollectionPoint;
import com.collectionpoint.model.dto.CollectionPointFilter;
import com.collectionpoint.model.dto.CollectionPointRequest;
import com.collectionpoint.model.dto.UserResponse;
import com.collectionpoint.model.gateways.CollectionPointRepository;
import com.collectionpoint.model.gateways.UserConsumerRespository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CollectionPointUseCase {
    private CollectionPointRepository collectionPointRepository;
    private UserConsumerRespository userConsumerRespository;

    public List<CollectionPoint> getAll(CollectionPointFilter collectionPointFilter) {
        return collectionPointRepository.getAll(collectionPointFilter);
    }

    public CollectionPoint getById(int id) {
        return collectionPointRepository.getById(id);
    }

    public CollectionPoint requestCollectionPoint(CollectionPointRequest collectionPointRequest) {
        CollectionPoint collectionPointResponse = null;
        try{
            UserResponse userResponse = userConsumerRespository.createUser(collectionPointRequest).block();
        } catch (Exception e){
            e.printStackTrace();
        }

        collectionPointResponse = collectionPointRepository.create(collectionPointRequest);
        return collectionPointResponse;
    }

}
