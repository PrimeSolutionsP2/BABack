package com.collectionpoint.jpa;

import com.collectionpoint.jpa.entity.CollectionPointEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface JPARepository extends CrudRepository<CollectionPointEntity, Integer>, QueryByExampleExecutor<CollectionPointEntity> {
    @Modifying
    @Query(value =
            "SELECT cp.* " +
            "FROM collection_point AS cp " +
            "JOIN user AS us " +
            "ON cp.user_id = us.id " +
            "WHERE " +
            "(:userId IS NULL OR us.id LIKE %:userId%) AND " +
            "(:userName IS NULL OR us.name LIKE %:userName%) AND " +
            "(:email IS NULL OR us.email LIKE %:email%) AND " +
            "(:name IS NULL OR cp.name LIKE %:name%) AND " +
            "(:address IS NULL OR cp.address LIKE %:address%) AND " +
            "(:city IS NULL OR cp.city LIKE %:city%) AND " +
            "(:state IS NULL OR cp.state LIKE %:state%) AND " +
            "(:country IS NULL OR cp.country LIKE %:country%) AND " +
            "(:stat IS NULL OR cp.status_id = :stat)",
        nativeQuery = true)
    List<CollectionPointEntity> findByFilters(
            @Param("userId") String userId,
            @Param("userName") String userName,
            @Param("email") String email,
            @Param("name") String name,
            @Param("address") String address,
            @Param("city") String city,
            @Param("state") String state,
            @Param("country") String country,
            @Param("stat") String status
    );
}
