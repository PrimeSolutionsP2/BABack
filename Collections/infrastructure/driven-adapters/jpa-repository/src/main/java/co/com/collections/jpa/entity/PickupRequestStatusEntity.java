package co.com.collections.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pickup_request_status")
@Data
public class PickupRequestStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
}
