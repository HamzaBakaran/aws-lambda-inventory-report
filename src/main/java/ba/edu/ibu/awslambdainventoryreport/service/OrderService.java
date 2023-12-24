package ba.edu.ibu.awslambdainventoryreport.service;

import ba.edu.ibu.awslambdainventoryreport.dto.OrderCustomDTO;
import ba.edu.ibu.awslambdainventoryreport.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderCustomDTO> findAllCustom() {
        List<OrderCustomDTO> orders = orderRepository.findAllCustom();
        return orders;
    }
}

