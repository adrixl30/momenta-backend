package com.momenta.controller;

import com.momenta.model.Order;
import com.momenta.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Map<String, String> data) {
        Long giftBoxId = Long.parseLong(data.get("giftBoxId"));
        String recipientName = data.get("recipientName");
        String recipientEmail = data.get("recipientEmail");

        Order order = orderService.createOrder(giftBoxId, recipientName, recipientEmail);
        return ResponseEntity.ok(order);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/my")
    public ResponseEntity<List<Order>> getMyOrders() {
        return ResponseEntity.ok(orderService.getOrdersForClient());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/mark-paid")
    public ResponseEntity<Order> markAsPaid(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.markAsPaid(id));
    }
}
