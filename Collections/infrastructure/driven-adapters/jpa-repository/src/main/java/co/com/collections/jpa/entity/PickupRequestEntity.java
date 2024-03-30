package co.com.collections.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "pickup_request")
@Data
public class PickupRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "storage_id")
    private Integer storageId;
    private Integer kilograms;
    @Column(name = "pickup_date")
    private LocalDateTime pickupDate;
}
