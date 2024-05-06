package co.com.collections.usecase.pickuprequest.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CompletePickupRequestDTO {
    private String pickupRequestId;
    private String aditionalCommentary;
    private Integer kilogramsRecolected;
}
