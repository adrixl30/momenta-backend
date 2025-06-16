package com.momenta.service;

import com.momenta.model.GiftBox;
import com.momenta.model.Order;
import com.momenta.model.OrderStatus;
import com.momenta.model.User;
import com.momenta.repository.GiftBoxRepository;
import com.momenta.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GiftBoxRepository giftBoxRepository;

    @Autowired
    private UserService userService;

    public Order createOrder(Long giftBoxId, String recipientName, String recipientEmail) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = (User) userService.loadUserByUsername(email);

        GiftBox giftBox = giftBoxRepository.findById(giftBoxId)
                .orElseThrow(() -> new IllegalArgumentException("GiftBox no encontrada"));

        Order order = Order.builder()
                .client(user)
                .giftBox(giftBox)
                .recipientName(recipientName)
                .recipientEmail(recipientEmail)
                .orderDate(LocalDate.now())
                .status(OrderStatus.PENDING)
                .build();

        return orderRepository.save(order);
    }

    public List<Order> getOrdersForClient() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = (User) userService.loadUserByUsername(email);
        return orderRepository.findByClient(user);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order markAsPaid(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada"));

        order.setStatus(OrderStatus.PAID);
        return orderRepository.save(order);
    }
}
