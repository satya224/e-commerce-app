package org.ecommerce.orderservice.orderline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    Collection<OrderLine> findAllByOrderId(Integer orderId);
}
