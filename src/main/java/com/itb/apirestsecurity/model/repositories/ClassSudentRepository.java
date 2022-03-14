package com.itb.apirestsecurity.model.repositories;

import com.itb.apirestsecurity.model.entities.ClassStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassSudentRepository extends JpaRepository<ClassStudent, Long> {
}