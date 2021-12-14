package com.management.warehouse.model.container;

import java.util.List;
import java.util.stream.Collectors;

public class ContainerConverter {

    public static ContainerDto convertToContainerDto(Container container) {
        if (container == null) return null;
        return ContainerDto.builder()
                .id(container.getId())
                .amount(container.getAmount())
                .name(container.getName())
                .width(container.getWidth())
                .length(container.getLength())
                .height(container.getHeight())
                .price(container.getPrice())
                .build();
    }

    public static Container convertToContainer(ContainerDto containerDto) {
        if (containerDto == null) return null;
        return Container.builder()
                .id(containerDto.getId())
                .amount(containerDto.getAmount())
                .name(containerDto.getName())
                .width(containerDto.getWidth())
                .length(containerDto.getLength())
                .height(containerDto.getHeight())
                .price(containerDto.getPrice())
                .build();
    }

    public static List<Container> convertContainerDtoToList(List<ContainerDto> containerDtos) {
        return containerDtos.stream()
                .map(ContainerConverter::convertToContainer)
                .collect(Collectors.toList());
    }

    public static List<ContainerDto> convertContainerListToDtoList(List<Container> containerList) {
        return containerList.stream()
                .map(ContainerConverter::convertToContainerDto)
                .collect(Collectors.toList());
    }
}
