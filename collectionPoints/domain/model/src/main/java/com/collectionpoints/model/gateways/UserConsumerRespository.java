package com.collectionpoints.model.gateways;

import com.collectionpoints.model.dto.UserResponse;
import com.collectionpoints.model.dto.CollectionPointRequest;
import reactor.core.publisher.Mono;

public interface UserConsumerRespository {
    Mono<UserResponse> createUser(CollectionPointRequest collectionPointRequest);
}
