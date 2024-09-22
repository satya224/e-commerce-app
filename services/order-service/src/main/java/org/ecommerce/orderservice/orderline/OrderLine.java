package org.ecommerce.orderservice.orderline;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.ecommerce.orderservice.order.Order;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer productId;
    private Double quantity;
}
