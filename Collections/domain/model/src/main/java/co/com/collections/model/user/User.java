package co.com.collections.model.user;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private Integer roleId;
}
