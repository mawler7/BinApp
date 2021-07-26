package com.management.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "container")
public class Container implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTAINER_ID")
    private int id;
    @Column(name = "AMOUNT")
    private int amount;
    @Column(name = "CONTAINER_NAME", unique = true)
    private String containerName;
    @Column(name = "CONTAINER_WIDTH")
    private int width;
    @Column(name = "CONTAINER_LENGTH")
    private int length;
    @Column(name = "CONTAINER_HEIGHT")
    private int height;
    @Column(name = "CONTAINER_VOLUME")
    private double volume;
    @Column(name = "CONTAINER_PRICE")
    private double price;
    @Column(name = "CONTAINER_TOTAL")
    private double containerTotal;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "container_order",
            joinColumns = @JoinColumn(name = "CONTAINER_ID_ID"),
            inverseJoinColumns = @JoinColumn(name = "ORDER_ID_ID"))
    private List<Container> containers;

    public Container(int id, int amount, String containerName, int width, int length, int height, double volume, double price, List<Container> containers) {
        this.id = id;
        this.amount = amount;
        this.containerName = containerName;
        this.width = width;
        this.length = length;
        this.height = height;
        this.volume = volume;
        this.price = price;
        this.containers = containers;
    }
}
