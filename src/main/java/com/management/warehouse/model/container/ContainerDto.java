package com.management.warehouse.model.container;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContainerDto {

    private UUID id;
    private int amount;
    private String containerName;
    private int width;
    private int length;
    private int height;
    private double volume;
    private double price;
    private double containerTotal;

}
