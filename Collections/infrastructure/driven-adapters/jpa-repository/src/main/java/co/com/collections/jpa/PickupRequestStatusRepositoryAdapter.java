package co.com.collections.jpa;

import co.com.collections.jpa.entity.PickupRequestStatusEntity;
import co.com.collections.jpa.helper.AdapterOperations;
import co.com.collections.model.pickuprequest.PickupRequest;
import co.com.collections.model.pickuprequeststatus.PickupRequestStatus;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PickupRequestStatusRepositoryAdapter extends AdapterOperations<PickupRequestStatus, PickupRequestStatusEntity, String, PickupRequestStatusRepository>
implements co.com.collections.model.pickuprequeststatus.gateways.PickupRequestStatusRepository {

    public PickupRequestStatusRepositoryAdapter(PickupRequestStatusRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, PickupRequestStatus.PickupRequestStatusBuilder.class).build());
    }
    @Override
    public List<PickupRequestStatus> getPickupRequestStatus() {
        return this.findAll();
    }
}
