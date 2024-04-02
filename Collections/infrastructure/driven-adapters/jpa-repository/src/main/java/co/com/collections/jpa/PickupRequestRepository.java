package co.com.collections.jpa;

import co.com.collections.jpa.entity.PickupRequestEntity;
import co.com.collections.model.pickuprequest.PickupRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PickupRequestRepository extends CrudRepository<PickupRequestEntity, String>, QueryByExampleExecutor<PickupRequestEntity> {
}
