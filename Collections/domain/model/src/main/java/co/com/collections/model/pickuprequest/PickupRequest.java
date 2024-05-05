package co.com.collections.model.pickuprequest;
import co.com.collections.model.user.User;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PickupRequest {
    private Integer id;
    private String userId;
    private Integer collectionPointId;
    private Integer kilograms;
    private LocalDateTime pickupDate;
    private String commentary;
    private LocalDateTime dateCreate;
    private Integer pickupRequestStatusId;

}
