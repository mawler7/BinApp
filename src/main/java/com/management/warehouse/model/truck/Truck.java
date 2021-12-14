package com.management.warehouse.model.truck;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TRUCKS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Truck implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false)
    private String regNumber;

    @Column(nullable = false)
    private String truckType;

    @Column(nullable = false)
    private int maxVolume;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Truck truck = (Truck) o;

        return new EqualsBuilder().append(maxVolume, truck.maxVolume).append(id, truck.id).append(regNumber, truck.regNumber).append(truckType, truck.truckType).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(regNumber).append(truckType).append(maxVolume).toHashCode();
    }
}

