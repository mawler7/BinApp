package com.management.warehouse.model.container;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "CONTAINERS")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Container implements Serializable {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int width;

    @Column(nullable = false)
    private int length;

    @Column(nullable = false)
    private int height;

    @Column(nullable = false)
    private double volume;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double total;


}
