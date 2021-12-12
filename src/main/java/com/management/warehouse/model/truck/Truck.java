package com.management.warehouse.model.truck;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

