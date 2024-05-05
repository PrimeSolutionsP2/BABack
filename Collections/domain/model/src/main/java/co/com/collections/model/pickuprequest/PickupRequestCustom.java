package co.com.collections.model.pickuprequest;
import co.com.collections.model.collectionpoint.CollectionPointCustom;
import co.com.collections.model.pickuprequeststatus.PickupRequestStatus;
import co.com.collections.model.user.User;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PickupRequestCustom {
    private Integer id;
    private User user;
    private CollectionPointCustom collectionPoint;
    private Integer kilograms;
    private LocalDateTime pickupDate;
    private String commentary;
    private LocalDateTime dateCreate;
    private PickupRequestStatus pickupRequestStatus;

}
