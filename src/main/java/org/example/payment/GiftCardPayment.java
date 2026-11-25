package org.example.payment;

import org.example.model.PaymentResult;

public class GiftCardPayment extends PaymentMethod {
    private final String code;
    private double balance;

    public GiftCardPayment(String code, double balance) {
        super("GiftCard");
        this.code = code;
        this.balance = Math.max(0, balance);
    }

    @Override
    public PaymentResult processPayment(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            return new PaymentResult(false, "Payment amount must be greater than zero.");
        }
        if (code == null || code.isBlank()) {
            return new PaymentResult(false, "Gift card code is required.");
        }
        if (amount > balance) {
            return new PaymentResult(false, "Insufficient gift card balance.");
        }

        balance -= amount;
        return new PaymentResult(true, "Paid " + amount + " using gift card");
    }
}
