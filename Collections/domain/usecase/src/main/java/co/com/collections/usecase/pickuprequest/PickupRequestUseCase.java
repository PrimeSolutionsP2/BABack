package co.com.collections.usecase.pickuprequest;

import co.com.collections.model.pickuprequest.PickupRequest;
import co.com.collections.model.pickuprequest.gateways.PickupRequestRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PickupRequestUseCase {
    private final PickupRequestRepository pickupRequestRepository;

    public PickupRequest getPickupRequest(String id) {
        return pickupRequestRepository.getPickupRequest(id);
    }

    public PickupRequest updatePickupRequest(PickupRequest pickupRequest) {
        return pickupRequestRepository.updatePickupRequest(pickupRequest);
    }

    public PickupRequest createPickupRequest(PickupRequest data) {
        PickupRequest pickupRequest = PickupRequest.builder()
                .storageId(data.getStorageId())
                .pickupDate(data.getPickupDate())
                .build();
        return pickupRequestRepository.createPickupRequest(pickupRequest);
    }

    public List<PickupRequest> getPickupRequests() {
        return pickupRequestRepository.getPickupRequests();
    }
}
