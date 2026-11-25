package org.example.payment;

import org.example.model.PaymentResult;

public class BankTransferPayment extends PaymentMethod {
    private final String iban;

    public BankTransferPayment(String iban) {
        super("BankTransfer");
        this.iban = iban;
    }

    @Override
    public PaymentResult processPayment(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            return new PaymentResult(false, "Payment amount must be greater than zero.");
        }
        if (iban == null || iban.isBlank()) {
            return new PaymentResult(false, "Bank account / IBAN is required.");
        }

        String cleanedIban = iban.replaceAll("\\s+", "").toUpperCase();
        String lastFour = cleanedIban.length() > 4
                ? cleanedIban.substring(cleanedIban.length() - 4)
                : cleanedIban;

        return new PaymentResult(
                true,
                "Paid " + amount + " via bank transfer to account ending with " + lastFour
        );
    }
}
