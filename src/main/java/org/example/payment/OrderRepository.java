package org.example.payment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.example.model.Order;

public class OrderRepository {
    private final List<Order> completedOrders = new ArrayList<>();

    public void save(Order order) {
        if (order != null && order.isPaid() && !completedOrders.contains(order)) {
            completedOrders.add(order);
        }
    }

    public List<Order> findAll() {
        return Collections.unmodifiableList(completedOrders);
    }
}
