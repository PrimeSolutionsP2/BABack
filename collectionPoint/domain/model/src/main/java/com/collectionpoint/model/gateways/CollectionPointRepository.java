package com.collectionpoint.model.gateways;

import com.collectionpoint.model.CollectionPoint;
import com.collectionpoint.model.CollectionPointFilter;

import java.util.List;

public interface CollectionPointRepository {
    List<CollectionPoint> getAll(CollectionPointFilter collectionPointFilter);
    CollectionPoint getById(int id);
}
