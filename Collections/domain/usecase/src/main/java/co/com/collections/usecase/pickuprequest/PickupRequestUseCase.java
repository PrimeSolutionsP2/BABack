package co.com.collections.usecase.pickuprequest;

import co.com.collections.model.pickuprequest.PickupRequest;
import co.com.collections.model.pickuprequest.PickupRequestCustom;
import co.com.collections.model.pickuprequest.gateways.PickupRequestRepository;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;


@RequiredArgsConstructor
public class PickupRequestUseCase {
    private final PickupRequestRepository pickupRequestRepository;
    private final Integer pickupRequestPendingStatusId = 1;
    public PickupRequest getPickupRequest(String id) {
        return pickupRequestRepository.getPickupRequest(id);
    }

    public PickupRequest updatePickupRequest(PickupRequest pickupRequest) {
        return pickupRequestRepository.updatePickupRequest(pickupRequest);
    }

    public PickupRequest createPickupRequest(PickupRequest data) {


        PickupRequest pickupRequest = PickupRequest.builder()
                .collectionPointId(data.getCollectionPointId())
                .pickupDate(data.getPickupDate())
                .commentary(data.getCommentary())
                .dateCreate(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC))
                .pickupRequestStatusId(this.pickupRequestPendingStatusId)
                .build();
        return pickupRequestRepository.createPickupRequest(pickupRequest);
    }

    public List<PickupRequestCustom> getPickupRequestsCustom(Integer pickupRequestStatusId, String searchFilterValue) {
        return pickupRequestRepository.getPickupRequestsCustom(pickupRequestStatusId, searchFilterValue);
    }
}
