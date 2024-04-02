package com.collectionpoint.consumer;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ObjectRequest {
    private String id;
    private String name;
    private String last_name;
    private String mail;
    private String phone_number;
    private final int type = 1;
}
