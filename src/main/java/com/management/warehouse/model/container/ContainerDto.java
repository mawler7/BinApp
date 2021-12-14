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
    private String name;
    private double width;
    private double length;
    private double height;
    private double price;
    private int amount;

}
