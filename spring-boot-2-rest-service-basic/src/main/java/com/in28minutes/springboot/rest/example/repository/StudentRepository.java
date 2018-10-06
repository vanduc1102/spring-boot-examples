package com.in28minutes.springboot.rest.example.repository;

import com.in28minutes.springboot.rest.example.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {}
