package com.edms.backend.repository;

import com.edms.backend.entity.FormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<FormEntity, Integer> {
    
    List<FormEntity> findByStationCode_Id(int stationCodeId);
    @Query("SELECT f FROM FormEntity f " +
    "LEFT JOIN FETCH f.stationCode " +
    "LEFT JOIN FETCH f.stationType " +
    "LEFT JOIN FETCH f.region " +
    "LEFT JOIN FETCH f.category " +
    "LEFT JOIN FETCH f.uploadType " +
    "WHERE LOWER(f.documentType) LIKE LOWER(CONCAT('%', :documentType, '%'))")
List<FormEntity> findByDocumentTypeContainingIgnoreCase(@Param("documentType") String documentType);


    @Query("SELECT COUNT(f) FROM FormEntity f WHERE LOWER(f.documentType) LIKE LOWER(CONCAT('%', :documentType, '%'))")
    long countByDocumentTypeContainingIgnoreCase(@Param("documentType") String documentType);


    List<FormEntity> findByUser_Id(int userId);

    @Query("SELECT f FROM FormEntity f " +
    "LEFT JOIN FETCH f.region " +
    "WHERE f.region.id = :regionId")
List<FormEntity> findByRegionId(@Param("regionId") int regionId);


@Query("SELECT f FROM FormEntity f WHERE LOWER(f.drawingNo) LIKE LOWER(CONCAT('%', :drawingNo, '%'))")
List<FormEntity> findByDrawingNoContainingIgnoreCase(@Param("drawingNo") String drawingNo);



@Query("SELECT f FROM FormEntity f " +
"LEFT JOIN FETCH f.category " +
"WHERE f.category.id = :categoryId")
List<FormEntity> findByCategoryId(@Param("categoryId") int categoryId);

@Query("SELECT COUNT(f) FROM FormEntity f WHERE f.category.id = :categoryId")
long countByCategoryId(@Param("categoryId") int categoryId);


}
