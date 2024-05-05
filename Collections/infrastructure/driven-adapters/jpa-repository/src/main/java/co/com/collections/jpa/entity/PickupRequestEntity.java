package co.com.collections.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;

@Entity
@Table(name = "pickup_request")
@Data
public class PickupRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "collection_point_id ")
    private Integer collectionPointId;
    private Integer kilograms;
    @Column(name = "pickup_date")
    private LocalDateTime pickupDate;
    @Column(name = "commentary")
    private String commentary;
    @Column(name = "date_create")
    private LocalDateTime dateCreate;
    @Column(name = "pickup_request_status_id")
    private Integer pickupRequestStatusId;
}
