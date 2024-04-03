package com.collectionpoint.consumer;

import com.collectionpoint.model.dto.UserResponse;
import com.collectionpoint.model.dto.CollectionPointRequest;
import com.collectionpoint.model.gateways.UserConsumerRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RestConsumer implements UserConsumerRespository {
    private final WebClient client;

    @Override
    public Mono<UserResponse> createUser(CollectionPointRequest collectionPointRequest) {
        ObjectRequest request = ObjectRequest.builder()
                .id(collectionPointRequest.getUserId())
                .name(collectionPointRequest.getUserName())
                .last_name(collectionPointRequest.getLastName())
                .phone_number(collectionPointRequest.getPhoneNumber())
                .mail(collectionPointRequest.getEmail())
                .build();
        return client
                .post()
                .body(Mono.just(request), ObjectRequest.class)
                .retrieve()
                .bodyToMono(UserResponse.class);
    }
}
