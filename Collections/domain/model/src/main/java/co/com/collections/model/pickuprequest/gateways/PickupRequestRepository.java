package co.com.collections.model.pickuprequest.gateways;

import co.com.collections.model.pickuprequest.PickupRequest;
import co.com.collections.model.pickuprequest.PickupRequestCustom;
import co.com.collections.model.pickuprequest.dto.CollectionByStateAndDateHistoricDTO;
import co.com.collections.model.pickuprequest.dto.CollectionByStateHistoricDTO;

import java.util.List;

public interface PickupRequestRepository {

    PickupRequest getPickupRequest(String id);
    PickupRequest updatePickupRequest(PickupRequest pickupRequest);
    PickupRequest createPickupRequest(PickupRequest pickupRequest);

    List<PickupRequestCustom> getPickupRequestsCustom(Integer pickupRequestStatusId, String searchFilterValue, String recollectorUserId,String id);
    List<CollectionByStateAndDateHistoricDTO> getCollectionsByStateAndDateHistoric(String filterState, Integer filterMonth, Integer filterYear);
    List<CollectionByStateHistoricDTO> getCollectionsByStateHistoric();

}
