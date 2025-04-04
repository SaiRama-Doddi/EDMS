package com.edms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edms.backend.entity.UploadedFile;

public interface UploadedFileRepository extends JpaRepository<UploadedFile, Integer> {
}
