package com.collectionpoint.model.gateways;

import com.collectionpoint.model.CollectionPoint;
import com.collectionpoint.model.dto.CollectionPointFilter;
import com.collectionpoint.model.dto.CollectionPointRequest;

import java.util.List;

public interface CollectionPointRepository {
    List<CollectionPoint> getAll(CollectionPointFilter collectionPointFilter);
    CollectionPoint getById(int id);
    CollectionPoint create(CollectionPointRequest collectionPointRequest);
}
