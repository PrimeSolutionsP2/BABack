package co.com.collections.model.collectionpoint;
import co.com.collections.model.user.User;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CollectionPointCustom {
       private Integer id;
       private User user;
       private Integer statusId;
       private String agreementCode;
       private String address;
       private String city;
       private String state;
       private String country;
}
