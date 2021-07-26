package com.management.warehouse.service;

import com.management.warehouse.model.Container;
import com.management.warehouse.repository.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContainerServiceImpl implements ContainerService {

    @Autowired
    ContainerRepository containerRepository;

    @Override
    public List<Container> getAllContainers() {
        return containerRepository.findAll();
    }

    @Override
    public Page<Container> getContainersPaginated(Pageable pageable) {
        return containerRepository.findAll(pageable);
    }


    @Override
    public Container findById(int id) {
        return containerRepository.getById(id);
    }


    @Override
    public void save(Container container) {
        containerRepository.save(container);
    }
}
