package com.management.warehouse.model.truck;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TruckDto {

    private UUID id;
    private String regNumber;
    private String truckType;
    private int maxVolume;
}
