package com.collectionpoint.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionPointFilter {

    public String userId;
    public String name;
    public String email;
    public String address;
    public String city;
    public String state;
    public String country;
    public String status;
}
