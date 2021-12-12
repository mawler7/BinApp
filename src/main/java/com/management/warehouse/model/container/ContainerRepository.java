package com.management.warehouse.model.container;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContainerRepository extends JpaRepository<Container, UUID> {
}
