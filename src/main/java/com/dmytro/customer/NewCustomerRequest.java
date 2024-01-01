package com.dmytro.customer;

public record NewCustomerRequest(
        String name,
        String email,
        Integer age
) {
}