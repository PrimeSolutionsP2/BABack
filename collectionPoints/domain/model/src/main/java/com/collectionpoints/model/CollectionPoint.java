package com.collectionpoints.model;

import lombok.Data;

@Data
public class CollectionPoint {
    private int id;
    private String userId;
    private String name;
    private String agreement;
    private String address;
    private String city;
    private String state;
    private String country;
    private int statusId;
    private Status status;

    // TODO
    // Implement document system
    //private int idDocument;

}
