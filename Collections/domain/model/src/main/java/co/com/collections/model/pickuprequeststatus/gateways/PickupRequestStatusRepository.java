package co.com.collections.model.pickuprequeststatus.gateways;

import co.com.collections.model.pickuprequeststatus.PickupRequestStatus;

import java.util.List;

public interface PickupRequestStatusRepository {
    List<PickupRequestStatus> getPickupRequestStatus();
}
