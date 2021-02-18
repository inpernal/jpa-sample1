package com.example.jpa.domain.delivery;

public enum DeliveryStatus {
    READY("준비"),
    COMP("배송");

    private String description;

    DeliveryStatus(String description) {
        this.description = description;
    }
}
