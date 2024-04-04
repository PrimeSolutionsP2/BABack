package com.collectionpoint.model.gateways;

import com.collectionpoint.model.dto.UserResponse;
import com.collectionpoint.model.dto.CollectionPointRequest;
import reactor.core.publisher.Mono;

public interface UserConsumerRespository {
    Mono<UserResponse> createUser(CollectionPointRequest collectionPointRequest);
}
