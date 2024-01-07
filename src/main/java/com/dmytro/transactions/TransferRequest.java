package com.dmytro.transactions;

public class TransferRequest {
    private Integer fromAccountId;
    private Integer toAccountId;
    private double amount;

    // Constructor
    public TransferRequest() {}

    // Getters and Setters
    public Integer getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Integer fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Integer getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Integer toAccountId) {
        this.toAccountId = toAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

