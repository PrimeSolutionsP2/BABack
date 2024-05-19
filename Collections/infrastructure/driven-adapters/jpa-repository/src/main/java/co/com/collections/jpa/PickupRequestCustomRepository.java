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
            "INNER JOIN pr.pickupRequestStatus prs " +
            "LEFT JOIN pr.user u " +
            "WHERE (:pickupRequestStatusId IS NULL OR prs.id = :pickupRequestStatusId) " +
            "AND (:id IS NULL OR pr.id = :id)"+
            "AND (:recollectorUserId IS NULL OR u.id = :recollectorUserId) " +
            "AND (:filterSearchValue IS NULL OR " +
            "cp.address LIKE %:filterSearchValue% OR " +
            "cp.city LIKE %:filterSearchValue% OR " +
            "cp.state LIKE %:filterSearchValue% OR " +
            "cp.country LIKE %:filterSearchValue% OR " +
            "u.name LIKE %:filterSearchValue% OR " +
            "u.lastName LIKE %:filterSearchValue% OR " +
            "u.email LIKE %:filterSearchValue%)")
    List<PickupRequestCustomEntity> findByFilters(@Param("pickupRequestStatusId") Integer pickupRequestStatusId, @Param("filterSearchValue") String filterSearchValue, @Param("recollectorUserId") String recollectorUserId, @Param("id") String id);
}
