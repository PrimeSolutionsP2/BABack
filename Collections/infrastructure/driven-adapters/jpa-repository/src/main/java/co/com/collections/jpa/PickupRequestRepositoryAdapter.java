package co.com.collections.jpa;

import co.com.collections.jpa.entity.PickupRequestEntity;
import co.com.collections.jpa.helper.AdapterOperations;
import co.com.collections.model.pickuprequest.PickupRequest;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PickupRequestRepositoryAdapter extends AdapterOperations<PickupRequest, PickupRequestEntity, String, PickupRequestRepository>
implements co.com.collections.model.pickuprequest.gateways.PickupRequestRepository
{

    public PickupRequestRepositoryAdapter(PickupRequestRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.mapBuilder(d,PickupRequest.PickupRequestBuilder.class).build());
    }


    @Override
    public PickupRequest getPickupRequest(String id) {
        return this.findById(id);
    }

    @Override
    public PickupRequest updatePickupRequest(PickupRequest pickupRequest) {
        return null;
    }

    @Override
    public PickupRequest createPickupRequest(PickupRequest pickupRequest) {
        return this.save(pickupRequest);
    }

    @Override
    public List<PickupRequest> getPickupRequests() {
        return this.findAll().stream().toList();
    }

}
