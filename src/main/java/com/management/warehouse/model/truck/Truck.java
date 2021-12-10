package com.management.warehouse.model.truck;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Truck {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String regNumber;

    @Column(nullable = false)
    private String truckType;

    @Column(nullable = false)
    private int maxVolume;

}

