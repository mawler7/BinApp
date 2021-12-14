package com.management.warehouse.model.truck;


import java.util.List;
import java.util.stream.Collectors;

public class TruckConverter {

    public static TruckDto convertToTruckDto(Truck truck) {
        if (truck == null) return null;
        return TruckDto.builder()
                .id(truck.getId())
                .regNumber(truck.getRegNumber())
                .truckType(truck.getTruckType())
                .maxVolume(truck.getMaxVolume())
                .build();
    }

    public static Truck convertToTruck(TruckDto truckDto) {
        if (truckDto == null) return null;
        return Truck.builder()
                .id(truckDto.getId())
                .regNumber(truckDto.getRegNumber())
                .truckType(truckDto.getTruckType())
                .maxVolume(truckDto.getMaxVolume())
                .build();
    }

    public static List<Truck> convertTruckDtoToList(List<TruckDto> truckDtos) {
        return truckDtos.stream()
                .map(TruckConverter::convertToTruck)
                .collect(Collectors.toList());
    }

    public static List<TruckDto> convertTruckListToDtoList(List<Truck> truckList) {
        return truckList.stream()
                .map(TruckConverter::convertToTruckDto)
                .collect(Collectors.toList());
    }

}
