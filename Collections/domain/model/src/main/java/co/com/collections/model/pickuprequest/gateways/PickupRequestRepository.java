package co.com.collections.model.pickuprequest.gateways;

import co.com.collections.model.pickuprequest.PickupRequest;

import java.util.List;

public interface PickupRequestRepository {

    PickupRequest getPickupRequest(String id);
    PickupRequest updatePickupRequest(PickupRequest pickupRequest);
    PickupRequest createPickupRequest(PickupRequest pickupRequest);

    List<PickupRequest> getPickupRequests();

}
