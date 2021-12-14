package com.management.warehouse.model.order;

import com.management.warehouse.model.container.Container;
import com.management.warehouse.model.truck.Truck;
import com.management.warehouse.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDto {

    private UUID id;
    private Truck truck;
    private Container container;
    private int amount;
    private String type;
    private User user;
}