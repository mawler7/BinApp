package com.management.warehouse.model.container;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContainerService {


    private final ContainerRepository containerRepository;

    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    public List<ContainerDto> getContainerList() {
       List<Container> containerList = containerRepository.findAll();
       return ContainerConverter.convertContainerListToDtoList(containerList);

    }


    public Page<Container> getContainersPaginated(Pageable pageable) {
        return containerRepository.findAll(pageable);
    }


    public Container findById(UUID id) {
        return containerRepository.getById(id);
    }

    public void save(Container container) {
        containerRepository.save(container);
    }
}
