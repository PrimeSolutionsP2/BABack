package com.collectionpoints.model.gateways;

import com.collectionpoints.model.CollectionPoint;
import com.collectionpoints.model.dto.CollectionPointFilter;
import com.collectionpoints.model.dto.CollectionPointRequest;

import java.util.List;

public interface CollectionPointRepository {
    List<CollectionPoint> getAll(CollectionPointFilter collectionPointFilter);
    CollectionPoint getById(int id);
    CollectionPoint create(CollectionPointRequest collectionPointRequest);
}
