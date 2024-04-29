package com.collectionpoints.usecase;

import com.collectionpoints.model.CollectionPoint;
import com.collectionpoints.model.dto.CollectionPointFilter;
import com.collectionpoints.model.dto.CollectionPointRequest;
import com.collectionpoints.model.dto.CollectionPointStatusChange;
import com.collectionpoints.model.dto.UserResponse;
import com.collectionpoints.model.gateways.CollectionPointRepository;
import com.collectionpoints.model.gateways.UserConsumerRespository;
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

    public CollectionPoint changeStatus(int id, CollectionPointStatusChange collectionPointStatusChange) {
        CollectionPoint collectionPoint = collectionPointRepository.getById(id);
        CollectionPoint response = null;

        if(collectionPoint != null && collectionPointStatusChange.getStatusId() != 1) {
            collectionPoint.setStatusId(collectionPointStatusChange.getStatusId());
            response = collectionPointRepository.create(collectionPoint);
        }

        return response;
    }

}
