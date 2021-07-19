package com.management.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.management.warehouse.model.Process;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends JpaRepository<Process,Integer> {
}
