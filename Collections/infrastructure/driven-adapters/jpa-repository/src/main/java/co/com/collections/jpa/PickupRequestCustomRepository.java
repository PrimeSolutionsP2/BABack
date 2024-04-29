package co.com.collections.jpa;

import co.com.collections.jpa.entity.PickupRequestCustomEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface PickupRequestCustomRepository extends CrudRepository<PickupRequestCustomEntity, String>, QueryByExampleExecutor<PickupRequestCustomEntity> {
    List<PickupRequestCustomEntity> findAll();
    @Query("SELECT pr FROM PickupRequestCustomEntity pr " +
            "INNER JOIN pr.collectionPoint cp " +
            "LEFT JOIN pr.user u " +
            "WHERE (:pickupRequestStatusId IS NULL OR pr.pickupRequestStatusId = :pickupRequestStatusId) " +
            "AND (:filterSearchValue IS NULL OR " +
            "cp.address LIKE %:filterSearchValue% OR " +
            "cp.city LIKE %:filterSearchValue% OR " +
            "cp.state LIKE %:filterSearchValue% OR " +
            "cp.country LIKE %:filterSearchValue% OR " +
            "u.name LIKE %:filterSearchValue% OR " +
            "u.lastName LIKE %:filterSearchValue% OR " +
            "u.email LIKE %:filterSearchValue%)")
    List<PickupRequestCustomEntity> findByFilters(@Param("pickupRequestStatusId") Integer pickupRequestStatusId, @Param("filterSearchValue") String filterSearchValue);
}