package com.collectionpoints.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionPointFilter {

    private String search;
    private String statusId;
}
