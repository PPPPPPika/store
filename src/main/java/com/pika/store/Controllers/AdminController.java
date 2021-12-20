package com.pika.store.Controllers;

import com.pika.store.Models.Order;
import com.pika.store.Repositorys.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/shop")
public class AdminController {
    private final OrderRepository orderRepository;

    @Autowired
    public AdminController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/adminpage")
    public Flux<Order> getOrders(){
        return orderRepository.findAll();
    }
}
