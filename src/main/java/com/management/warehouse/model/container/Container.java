package com.management.warehouse.model.container;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
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
    private int containersAmount;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private double width;

    @Column(nullable = false)
    private double length;

    @Column(nullable = false)
    private double height;

    @Column(nullable = false)
    private double volume;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Container container = (Container) o;

        return new EqualsBuilder().append(containersAmount, container.containersAmount).append(width, container.width).append(length, container.length).append(height, container.height).append(volume, container.volume).append(price, container.price).append(total, container.total).append(id, container.id).append(name, container.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(containersAmount).append(name).append(width).append(length).append(height).append(volume).append(price).append(total).toHashCode();
    }
}
