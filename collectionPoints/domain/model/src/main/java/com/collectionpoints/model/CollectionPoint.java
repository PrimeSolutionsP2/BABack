package com.collectionpoints.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectionPoint {
    private int id;
    private String userId;
    private String name;
    private String agreement;
    private String address;
    private String city;
    private String state;
    private String country;
    private int statusId = 1;
    private Status status;

    // TODO
    // Implement document system
    //private int idDocument;

}
