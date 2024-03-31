package com.collectionpoint.usecase;

import com.collectionpoint.model.CollectionPoint;
import com.collectionpoint.model.CollectionPointFilter;
import com.collectionpoint.model.gateways.CollectionPointRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CollectionPointUseCase {
    private CollectionPointRepository collectionPointRepository;

    public List<CollectionPoint> getAll(CollectionPointFilter cpf) {
        return collectionPointRepository.getAll(cpf);
    }

    public CollectionPoint getById(int id) {
        return collectionPointRepository.getById(id);
    }

}
