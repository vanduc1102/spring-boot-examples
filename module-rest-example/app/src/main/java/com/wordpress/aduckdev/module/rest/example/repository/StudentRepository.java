package com.wordpress.aduckdev.module.rest.example.repository;

import com.wordpress.aduckdev.module.rest.example.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {}
