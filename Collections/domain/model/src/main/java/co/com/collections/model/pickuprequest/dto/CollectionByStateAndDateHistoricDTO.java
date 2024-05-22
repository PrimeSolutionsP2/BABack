package co.com.collections.model.pickuprequest.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CollectionByStateAndDateHistoricDTO {
    private String state;
    private Long totalKilogramsCollected;
    private LocalDateTime pickupDate;
}