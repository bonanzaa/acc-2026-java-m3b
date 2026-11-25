package org.example.payment;

import org.example.model.PaymentResult;

public class CreditCardPayment extends PaymentMethod {
    private final String cardNumber;
    private final String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        super("CreditCard");
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public PaymentResult processPayment(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            return new PaymentResult(false, "Payment amount must be greater than zero.");
        }
        if (cardHolderName == null || cardHolderName.isBlank()) {
            return new PaymentResult(false, "Cardholder name is required.");
        }

        String digitsOnly = cardNumber == null ? "" : cardNumber.replaceAll("[\\s-]", "");
        if (!digitsOnly.matches("\\d{12,19}")) {
            return new PaymentResult(false, "Invalid credit card number.");
        }

        return new PaymentResult(
                true,
                "Paid " + amount + " using credit card ending with " + digitsOnly.substring(digitsOnly.length() - 4)
        );
    }
}