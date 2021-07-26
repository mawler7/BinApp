package com.management.warehouse.service;

import com.management.warehouse.model.Container;
import com.management.warehouse.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContainerService {

    List<Container> getAllContainers();

    Page<Container> getContainersPaginated(Pageable pageable);

    Container findById(int id);

    void save(Container container);
}
