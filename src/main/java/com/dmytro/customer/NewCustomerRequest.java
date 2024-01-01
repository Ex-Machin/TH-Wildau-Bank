package com.dmytro.user;

public record NewUserRequest(
        String name,
        String email,
        Integer age
) {
}