package co.com.collections.model.pickuprequest;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PickupRequest {
    private String id;
    private Long userId;
    private Long storageId;
    private Integer kilograms;
    LocalDateTime pickupDate;
}
