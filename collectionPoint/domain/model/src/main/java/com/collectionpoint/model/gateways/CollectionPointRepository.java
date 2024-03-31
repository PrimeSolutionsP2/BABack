package com.collectionpoint.model.gateways;

import com.collectionpoint.model.CollectionPoint;

import java.util.List;

public interface CollectionPointRepository {
    List<CollectionPoint> getAll(String id_card, String name, String email, String address, String state, String country, boolean active);
    CollectionPoint getById(int id);
}
