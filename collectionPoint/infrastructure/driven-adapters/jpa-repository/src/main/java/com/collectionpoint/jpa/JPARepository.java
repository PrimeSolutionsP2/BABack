package com.collectionpoint.jpa;

import com.collectionpoint.jpa.entity.CollectionPointEntity;
import com.collectionpoint.model.CollectionPoint;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface JPARepository extends CrudRepository<CollectionPointEntity, Integer>, QueryByExampleExecutor<CollectionPointEntity> {
    @Modifying
    @Query(value =
            "SELECT storage_point.* " +
            "FROM storage_point " +
            "JOIN user " +
            "ON storage_point.user_id = user.id " +
            "WHERE " +
            "(:id_card IS NULL OR user.id_card=:id_card) AND " +
            "(:name IS NULL OR user.name=:name) AND " +
            "(:email IS NULL OR user.email = :email) AND " +
            "(:address IS NULL OR storage_point.address LIKE %:address%) AND " +
            "(:city IS NULL OR storage_point.city LIKE %:city%) AND " +
            "(:state IS NULL OR storage_point.state LIKE %:state%) AND " +
            "(:country IS NULL OR storage_point.country LIKE %:country%) AND " +
            "(:status IS NULL OR status = :status)",
        nativeQuery = true)
    List<CollectionPointEntity> findByFilters(
            @Param("id_card") String id_card,
            @Param("name") String name,
            @Param("email") String email,
            @Param("address") String address,
            @Param("city") String city,
            @Param("state") String state,
            @Param("country") String country,
            @Param("status") String status
    );
}
