package com.collectionpoints.jpa;

import com.collectionpoints.jpa.entity.CollectionPointEntity;
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
            "(:search IS NULL OR " +
            "us.id LIKE %:search% OR " +
            "cp.name LIKE %:search% OR " +
            "cp.address LIKE %:search% OR " +
            "cp.city LIKE %:search% OR " +
            "cp.state LIKE %:search% OR " +
            "cp.country LIKE %:search%) AND " +
            "(:statusId IS NULL OR cp.status_id = :statusId)",
        nativeQuery = true)
    List<CollectionPointEntity> findByFilters(
            @Param("search") String search,
            @Param("statusId") String statusId
    );

    Integer countByState(String state);

    @Query(value = "SELECT COUNT(*) " +
            "FROM collection_point " +
            "WHERE " +
            "state=:state AND " +
            "DATE(created_at) <= :date",
        nativeQuery = true)
    Integer count(@Param("state") String state, @Param("date") String date);
}
