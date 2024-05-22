package co.com.collections.model.pickuprequest.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CollectionByStateHistoricDTO {
    private String state;
    private Long totalKilogramsCollected;
}
