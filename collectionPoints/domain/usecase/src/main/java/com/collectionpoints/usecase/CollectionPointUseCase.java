package com.collectionpoints.usecase;

import com.collectionpoints.model.CollectionPoint;
import com.collectionpoints.model.dto.CollectionPointFilter;
import com.collectionpoints.model.dto.CollectionPointRequest;
import com.collectionpoints.model.dto.CollectionPointStatusChange;
import com.collectionpoints.model.dto.UserResponse;
import com.collectionpoints.model.exception.CustomException;
import com.collectionpoints.model.exception.HttpStatusCode;
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

    public CollectionPoint getById(int id) throws CustomException {
        CollectionPoint collectionPoint = collectionPointRepository.getById(id);

        if(collectionPoint == null) {
            throw new CustomException(HttpStatusCode.NOT_FOUND.getCode(), "Collection point with id: "+ id + ", was not found!");
        }

        return collectionPoint;
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

    public CollectionPoint changeStatus(int id, CollectionPointStatusChange collectionPointStatusChange) throws CustomException {
        CollectionPoint collectionPoint = collectionPointRepository.getById(id);

        if(collectionPoint == null) {
            throw new CustomException(HttpStatusCode.NOT_FOUND.getCode(), "Collection point with id: "+ id + ", was not found!");
        }
        if(collectionPointStatusChange.getStatusId() == 1){
            throw new CustomException(HttpStatusCode.BAD_REQUEST.getCode(), "Cannot change status to 1 (PENDIENTE)");
        }

        collectionPoint.setStatusId(collectionPointStatusChange.getStatusId());
        return collectionPointRepository.create(collectionPoint);
    }

    public CollectionPoint updateCollectionPoint(int id, String role, CollectionPoint collectionPoint) throws CustomException {
        CollectionPoint existingCollectionPoint = collectionPointRepository.getById(id);

        if(existingCollectionPoint == null) {
            throw new CustomException(HttpStatusCode.NOT_FOUND.getCode(), "Collection point with id: "+ id + ", was not found!");
        }

        if(collectionPoint.getName() != null) existingCollectionPoint.setName(collectionPoint.getName());
        if(collectionPoint.getAgreement() != null) existingCollectionPoint.setAgreement(collectionPoint.getAgreement());
        if(collectionPoint.getAddress() != null) existingCollectionPoint.setAddress(collectionPoint.getAddress());
        if(collectionPoint.getCity() != null) existingCollectionPoint.setCity(collectionPoint.getCity());
        if(collectionPoint.getState() != null) existingCollectionPoint.setState(collectionPoint.getState());
        if(collectionPoint.getCountry() != null) existingCollectionPoint.setCountry(collectionPoint.getCountry());
        if(collectionPoint.getUserIdFile() != null) existingCollectionPoint.setUserIdFile(collectionPoint.getUserIdFile());
        if(collectionPoint.getPlaceImage() != null) existingCollectionPoint.setPlaceImage(collectionPoint.getPlaceImage());

        if(role.equals("2") || role.equals("RECOLECTOR")){
            throw new CustomException(HttpStatusCode.BAD_REQUEST.getCode(), "As a recolector it is not possible to update the information of a collection point");
        }
        else if(role.equals("3") || role.equals("REPRESENTANTE")) {
            existingCollectionPoint.setStatusId(1);
        }

        return collectionPointRepository.create(existingCollectionPoint);
    }

}
