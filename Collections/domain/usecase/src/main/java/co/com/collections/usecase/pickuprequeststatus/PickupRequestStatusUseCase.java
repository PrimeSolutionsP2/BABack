package co.com.collections.usecase.pickuprequeststatus;

import co.com.collections.model.pickuprequeststatus.PickupRequestStatus;
import co.com.collections.model.pickuprequeststatus.gateways.PickupRequestStatusRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PickupRequestStatusUseCase {

    public final static  Integer PICKUP_REQUEST_STATUS_ID_PENDING = 1;
    public final static  Integer PICKUP_REQUEST_STATUS_ID_COMPLETE = 2;
    public final static  Integer PICKUP_REQUEST_STATUS_ID_SCHEDULED = 3;

    private final PickupRequestStatusRepository pickupRequestStatusRepository;

    public List<PickupRequestStatus> getPickupRequestStatus() {
        return pickupRequestStatusRepository.getPickupRequestStatus();
    }
}

