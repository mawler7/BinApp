package com.management.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
        private int id;
    @ManyToOne
        private Truck truck;
    @ManyToOne
        private Container container;
    @Column(name = "AMOUNT")
        private int amount;
    @ManyToOne
        private Process process;
    @ManyToOne
        private Supplier supplier;
    @Column(name = "DATE")
    private String date;
    @Column(name = "IS_DELIVERED")
    private boolean isDelivered = true;
    @Column(name = "DATE_DELIVERED")
    private LocalDateTime dateDelivered;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String  date) {
        this.date = date;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public LocalDateTime getDateDelivered() {
        return dateDelivered;
    }

    public void setDateDelivered(LocalDateTime dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

//    @JoinTable(name = "portal_user_roles",
//            joinColumns = @JoinColumn(name = "PUR_PU_ID"),
//            inverseJoinColumns = @JoinColumn(name = "PUR_RO_ID"))
//    private Set<Role> roles;


//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "container_order",
//            joinColumns = @JoinColumn(name= "CONTAINER_ID_ID"),
//            inverseJoinColumns = @JoinColumn(name = "ORDER_ID_ID"))
//    private List<Container> containers;
//
//    @OneToOne(targetEntity = Truck.class)
//    private Truck truck;
//
//    @OneToOne(targetEntity = Supplier.class)
//    private Supplier supplier;
//
//    @OneToOne(targetEntity = Process.class)
//    private Process process;
//
//
//    @ManyToMany(targetEntity = Truck.class, fetch = FetchType.LAZY, cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    private List<Truck> trucks;
//    @ManyToMany(targetEntity = Process.class, fetch = FetchType.LAZY, cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    private List<Process> processes;
//    @ManyToMany(targetEntity = Supplier.class, fetch = FetchType.LAZY, cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    private List<Supplier> suppliers;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "O_TRUCK_ID", referencedColumnName = "TRUCK_ID")
//    private Truck truck;
//    @JoinColumn(name = "O_CONTAINER_ID", referencedColumnName = "CONTAINER_ID")
//    private Container container;
//    @Column(name = "AMOUNT")
//    private int amount;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "O_PROCESS_ID", referencedColumnName = "PROCESS_ID")
//    private Process process;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "O_SUPPLIER_ID", referencedColumnName = "SUPPLIER_ID")
//    private Supplier supplier;
//    @Column(name = "DATE")
//    private String date;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ORD_PU_ID", referencedColumnName = "PU_ID")
//    private PortalUser portalUser;


}
