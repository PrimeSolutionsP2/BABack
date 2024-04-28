package com.collectionpoints.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionPointFilter {

    private String userId;
    private String userName;
    private String email;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String status;
}
