package co.com.collections.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;

@Entity
@Table(name = "pickup_request")
@Data
public class PickupRequestCustomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Fetch(FetchMode.SELECT)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "collection_point_id")
    @Fetch(FetchMode.SELECT)
    private CollectionPointCustomEntity collectionPoint;
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
