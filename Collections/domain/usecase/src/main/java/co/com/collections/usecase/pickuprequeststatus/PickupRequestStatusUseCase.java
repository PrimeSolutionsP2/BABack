package co.com.collections.usecase.pickuprequeststatus;

import co.com.collections.model.pickuprequeststatus.PickupRequestStatus;
import co.com.collections.model.pickuprequeststatus.gateways.PickupRequestStatusRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PickupRequestStatusUseCase {
    private final PickupRequestStatusRepository pickupRequestStatusRepository;

    public List<PickupRequestStatus> getPickupRequestStatus() {
        return pickupRequestStatusRepository.getPickupRequestStatus();
    }
}
