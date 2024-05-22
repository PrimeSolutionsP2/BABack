package co.com.collections.jpa;

import co.com.collections.jpa.entity.PickupRequestCustomEntity;
import co.com.collections.model.pickuprequest.dto.CollectionByStateAndDateHistoricDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import co.com.collections.model.pickuprequest.dto.CollectionByStateHistoricDTO;

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

    @Query("SELECT new co.com.collections.model.pickuprequest.dto.CollectionByStateHistoricDTO(cp.state, SUM(pr.kilograms) as totalKilogramsCollected)\n" +
            "FROM PickupRequestCustomEntity pr\n" +
            "JOIN pr.collectionPoint cp\n" +
            "GROUP BY cp.state\n" +
            "ORDER BY totalKilogramsCollected DESC")
    List<CollectionByStateHistoricDTO> findCollectionByStatesHistoric();

    @Query("SELECT new co.com.collections.model.pickuprequest.dto.CollectionByStateAndDateHistoricDTO(cp.state, SUM(pr.kilograms) as totalKilogramsCollected, pr.pickupDate)\n" +
            "FROM PickupRequestCustomEntity pr\n" +
            "JOIN pr.collectionPoint cp\n" +
            "WHERE cp.state = :filterState AND FUNCTION('MONTH', pr.pickupDate) = :filterMonth AND FUNCTION('YEAR', pr.pickupDate) = :filterYear\n" +
            "GROUP BY cp.state, pr.pickupDate\n" +
            "ORDER BY pr.pickupDate ASC")
    List<CollectionByStateAndDateHistoricDTO> findCollectionByStatesAndDateHistoric(@Param("filterState") String filterState, @Param("filterMonth") Integer filterMonth, @Param("filterYear") Integer filterYear);
}
