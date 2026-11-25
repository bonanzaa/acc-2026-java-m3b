package org.example.payment;

import org.example.model.Order;
import org.example.model.PaymentResult;

public class PaymentProcessor {
    public PaymentResult process(Order order, PaymentMethod paymentMethod) {
        if (order == null || paymentMethod == null) {
            return new PaymentResult(false, "Order and payment method are required.");
        }
        if (order.isPaid()) {
            return new PaymentResult(false, "Order is already paid.");
        }
        if (order.getItems().isEmpty()) {
            return new PaymentResult(false, "Cannot process payment for an empty order.");
        }

        double totalWithTax = order.calculateTotalWithTax(0.21);
        PaymentResult result = paymentMethod.processPayment(totalWithTax);

        if (result.isSuccessful()) {
            order.markAsPaid();
        }

        return result;
    }
}