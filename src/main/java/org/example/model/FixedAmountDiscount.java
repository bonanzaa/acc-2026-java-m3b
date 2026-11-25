package org.example.model;

public class FixedAmountDiscount extends Discount {
    private final double amount;

    public FixedAmountDiscount(String code, double amount) {
        super(code);
        this.amount = Math.max(0, amount);
    }

    @Override
    public double apply(double originalAmount) {
        return Math.max(0, originalAmount - amount);
    }
}