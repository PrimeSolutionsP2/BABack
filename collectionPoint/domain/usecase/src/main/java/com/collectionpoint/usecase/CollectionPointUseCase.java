package com.collectionpoint.usecase;

import com.collectionpoint.model.CollectionPoint;
import com.collectionpoint.model.gateways.CollectionPointRepository;

import java.util.List;

public class CollectionPointUseCase {
    private final CollectionPointRepository collectionPointRepository;

    public CollectionPointUseCase(CollectionPointRepository collectionPointRepository) {
        this.collectionPointRepository = collectionPointRepository;
    }

    public List<CollectionPoint> getAll(String id_card, String name, String email, String address, String state, String country, boolean active) {
        return collectionPointRepository.getAll(id_card, name, email, address, state, country, active);
    }

    public CollectionPoint getById(int id) {
        return collectionPointRepository.getById(id);
    }

}
