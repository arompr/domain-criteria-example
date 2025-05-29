package com.example.domain.user;

import java.util.UUID;

public class UserId {
    private final UUID value;

    public UserId(String value) {
        this.value = UUID.fromString(value);
    }

    public UserId(UUID uuid) {
        this.value = uuid;
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }

    public UUID getId() {
        return this.value;
    }

    public String toString() {
        return this.value.toString();
    }
}
