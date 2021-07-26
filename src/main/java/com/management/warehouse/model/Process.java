package com.management.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Table(name = "process")
@NoArgsConstructor
@AllArgsConstructor
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROCESS_ID")
    private int id;
    @Column(name = "PROCESS_TYPE")
    private String processType;
}
