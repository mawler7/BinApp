package com.management.warehouse.model.container;

import com.management.warehouse.exception.FieldDoesNotExistException;
import com.management.warehouse.exception.container.ContainerAlreadyExistException;
import com.management.warehouse.exception.container.ContainerNotFoundException;
import com.management.warehouse.exception.truck.TruckNotFoundException;
import com.management.warehouse.model.truck.Truck;
import com.management.warehouse.model.truck.TruckConverter;
import com.management.warehouse.model.truck.TruckDto;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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

    public ContainerDto registerNewContainer(ContainerDto containerDto) {
        if (containerRepository.findByNameAllIgnoreCase(containerDto.getName()) != null) {
            throw new ContainerAlreadyExistException("Container with the following name already exists: " + containerDto.getName());
        }
        Container container = ContainerConverter.convertToContainer(containerDto);
        container.setId(UUID.randomUUID());
        container.setVolume(container.getLength() * container.getHeight() * container.getWidth());
        container.setTotal(container.getPrice() * container.getContainersAmount());
        containerRepository.save(container);
        return ContainerConverter.convertToContainerDto(container);
    }

    private Container findContainerInDatabase(UUID id) {
        return containerRepository.findById(id).orElseThrow(() ->
                new ContainerNotFoundException("Could not find truck with id: " + id));
    }

    public ContainerDto findById(UUID id) {
        Container container = findContainerInDatabase(id);
        return ContainerConverter.convertToContainerDto(container);
    }

    public ContainerDto deleteContainer(UUID id){
        Container containerToDelete = findContainerInDatabase(id);
        containerRepository.deleteById(id);
        return ContainerConverter.convertToContainerDto(containerToDelete);
    }


    public ContainerDto updateContainer(UUID id, Map<Object, Object> fields) {
        Container container = findContainerInDatabase(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Container.class, (String) key);
            if (field == null) {
                throw new FieldDoesNotExistException("Field " + key + " does not exist");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, container, value);
        });
        containerRepository.save(container);
        return ContainerConverter.convertToContainerDto(container);
    }

    public ContainerDto editContainer(UUID id, ContainerDto containerDto){
        String nameBeforeChange = containerRepository.findById(id).get().getName();
        if (containerRepository.findAllByNameAllIgnoreCase(containerDto.getName()).size() > 1 ) {
            throw new ContainerAlreadyExistException("Container with the following name already exists: " + containerDto.getName());
        }

        Container container = findContainerInDatabase(id);
        container.setName(containerDto.getName());
        container.setWidth(containerDto.getWidth());
        container.setLength(containerDto.getLength());
        container.setHeight(containerDto.getHeight());
        container.setPrice(containerDto.getPrice());
        container.setContainersAmount(containerDto.getContainersAmount());
        containerRepository.save(container);
        if (containerRepository.findAllByNameAllIgnoreCase(containerDto.getName()).size() > 1 ) {
            container.setName(nameBeforeChange);
            containerRepository.save(container);
        }
        return ContainerConverter.convertToContainerDto(container);
    }

}
