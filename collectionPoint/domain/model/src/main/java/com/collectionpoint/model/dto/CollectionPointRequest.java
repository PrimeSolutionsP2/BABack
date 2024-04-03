package com.collectionpoint.model.dto;

import lombok.Data;

@Data
public class CollectionPointRequest {
    private String userId;
    private String userName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String name;
    private String agreement;
    private String address;
    private String city;
    private String state;
    private String country;
    private final int statusId = 1;
}
