package co.com.collections.jpa;

import co.com.collections.jpa.entity.PickupRequestEntity;
import co.com.collections.jpa.entity.PickupRequestStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PickupRequestStatusRepository extends CrudRepository<PickupRequestStatusEntity, String>, QueryByExampleExecutor<PickupRequestStatusEntity> {
}
