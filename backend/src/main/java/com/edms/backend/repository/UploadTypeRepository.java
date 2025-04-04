package com.edms.backend.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edms.backend.entity.UploadType;
import java.util.List;

@Repository
public interface UploadTypeRepository extends JpaRepository<UploadType, Integer> {
    List<UploadType> findByCategoryId(int categoryId);
}

