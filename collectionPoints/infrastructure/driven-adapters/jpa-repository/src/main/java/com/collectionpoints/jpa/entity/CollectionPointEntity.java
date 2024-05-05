package com.collectionpoints.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "collection_point")
public class CollectionPointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private String userId;
    private String address;
    private String name;
    @Column(name = "agreement_code")
    private String agreement;
    private String city;
    private String state;
    private String country;
    @Column(name = "status_id")
    private Integer statusId;
    @ManyToOne
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private StatusEntity status;
}
