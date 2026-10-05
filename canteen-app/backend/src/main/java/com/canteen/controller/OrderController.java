package com.canteen.controller;

import org.springframework.web.bind.annotation.*;

import com.canteen.model.Item;
import com.canteen.model.Order;
import com.canteen.repository.ItemRepository;
import com.canteen.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orders;
    private final ItemRepository items;

    public OrderController(OrderRepository orders,
                           ItemRepository items) {

        this.orders = orders;
        this.items = items;
    }

    public static class OrderLine {

        public Long id;

        public int qty;
    }

    public static class OrderRequest {
        public String paymentMethod;
        public String username;

        public List<OrderLine> items;
    }

   @PostMapping
public Order place(
        @RequestBody OrderRequest req) {

    double total = 0;

    List<String> parts =
        new ArrayList<>();

    for (OrderLine line : req.items) {

        Item it =
            items.findById(line.id)
                 .orElseThrow();

        total +=
            it.getPrice() * line.qty;

        parts.add(
            line.qty + "× " + it.getName()
        );
    }

    Order order = new Order(
        req.username,
        String.join(", ", parts),
        total
    );

    order.setPaymentMethod(
        req.paymentMethod
    );

    order.setPaymentStatus(
        "PENDING"
    );

    return orders.save(order);
}

    @GetMapping
    public List<Order> list() {

        return orders.findAll().stream()

            .sorted(
                Comparator.comparing(
                    Order::getCreatedAt
                ).reversed()
            )

            .collect(Collectors.toList());
    }

    @PutMapping("/{id}/status")
    public Order updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Order order =
            orders.findById(id)
                  .orElseThrow();

        order.setStatus(status);

        return orders.save(order);
    }
@PutMapping("/{id}/payment")
public Order updatePaymentStatus(
        @PathVariable Long id,
        @RequestParam String status) {

    Order order =
        orders.findById(id)
              .orElseThrow();

    order.setPaymentStatus(status);

    return orders.save(order);
}

}