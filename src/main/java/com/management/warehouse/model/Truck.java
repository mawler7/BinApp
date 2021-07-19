package com.management.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Truck{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRUCK_ID")
    private int id;
    @Column(name = "REG_NUMBER")
    private String regNumber;
    @Column(name = "TRUCK_TYPE")
    private String truckType;
    @Column(name = "MAX_VOL")
    private int maxVolume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public int getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(int maxVolume) {
        this.maxVolume = maxVolume;
    }
}

