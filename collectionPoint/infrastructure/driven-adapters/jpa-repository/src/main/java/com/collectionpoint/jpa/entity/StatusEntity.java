package com.collectionpoint.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "status")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
