package org.ecommerce.orderservice.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;

    public List<Integer> saveOrderLines(List<OrderLineRequest> request) {
        List<OrderLine> orderLines = OrderLineMapper.toOrderLines(request);
        return orderLineRepository.saveAll(orderLines).stream().map(OrderLine::getId).toList();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId).stream()
                .map(OrderLineMapper::toOrderLineResponse)
                .toList();
    }
}
