package com.management.warehouse.model.order;

import com.management.warehouse.model.container.Container;
import com.management.warehouse.model.truck.Truck;
import com.management.warehouse.model.user.User;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    private Truck truck;

    @ManyToOne
    private Container container;

    @Column(nullable = false)
    private int amountOfOrderedContainers;

    @Column(nullable = false)
    private String type;

    @JoinColumn
    @ManyToOne
    private User user;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private boolean delivered;

    @Column(nullable = false)
    private LocalDateTime dateDelivered;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return new EqualsBuilder().append(amountOfOrderedContainers, order.amountOfOrderedContainers).append(delivered, order.delivered).append(id, order.id).append(truck, order.truck).append(container, order.container).append(type, order.type).append(user, order.user).append(date, order.date).append(dateDelivered, order.dateDelivered).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(truck).append(container).append(amountOfOrderedContainers).append(type).append(user).append(date).append(delivered).append(dateDelivered).toHashCode();
    }
}
