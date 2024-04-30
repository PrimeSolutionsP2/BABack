package co.com.collections.usecase.pickuprequest.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdatePickupRequestRecollectorAndPickupDateDTO {
    private String pickupRequestId;
    private String recollectorId;
    private LocalDateTime pickupDate;

}
