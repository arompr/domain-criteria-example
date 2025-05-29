package com.example.infra.mariadb;

import com.example.domain.user.User;
import com.example.domain.user.UserId;

public class MariaUserMapper {
    public static MariaUser toDto(User user) {
        return new MariaUser(user.getId().getId(), user.getEmail(), user.getFirstName(), user.getLastName());
    }

    public static User toEntity(MariaUser mariaUser) {
        return new User(new UserId(mariaUser.id), mariaUser.email, mariaUser.firstName, mariaUser.name);
    }
}
