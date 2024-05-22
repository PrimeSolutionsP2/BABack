package com.collectionpoints.model.dto;

import com.collectionpoints.model.CollectionPoint;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CollectionPointRequest extends CollectionPoint {
    private String userName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
