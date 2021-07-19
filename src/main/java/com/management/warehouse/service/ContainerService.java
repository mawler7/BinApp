package com.management.warehouse.service;

import com.management.warehouse.model.Container;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContainerService {

    List<Container> getAllContainers();

    Page<Container> getContainersPaginated(Pageable pageable);
}
