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

    public CollectionPoint updateCollectionPoint(int id, CollectionPoint collectionPoint){
        CollectionPoint existingCollectionPoint = collectionPointRepository.getById(id);
        CollectionPoint response = null;

        if(existingCollectionPoint != null) {

            if(collectionPoint.getName() != null) existingCollectionPoint.setName(collectionPoint.getName());

            if(collectionPoint.getAgreement() != null) existingCollectionPoint.setAgreement(collectionPoint.getAgreement());

            if(collectionPoint.getAddress() != null) existingCollectionPoint.setAddress(collectionPoint.getAddress());

            if(collectionPoint.getCity() != null) existingCollectionPoint.setCity(collectionPoint.getCity());

            if(collectionPoint.getState() != null) existingCollectionPoint.setState(collectionPoint.getState());

            if(collectionPoint.getCountry() != null) existingCollectionPoint.setCountry(collectionPoint.getCountry());

            response = collectionPointRepository.create(existingCollectionPoint);
        }

        return response;
    }

}
