package co.com.collections.model.pickuprequeststatus;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PickupRequestStatus {
    private Integer id;
    private String name;
}
