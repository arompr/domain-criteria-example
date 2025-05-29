package com.example.domain.user.criteria;

import com.example.domain.query.criteria.ICriteria;

public class EmailEqualsCriteria implements ICriteria {
    private final String email;

    private EmailEqualsCriteria(String email) {
        this.email = email;
    }

    public static EmailEqualsCriteria hasEmail(String email) {
        return new EmailEqualsCriteria(email);
    }

    public String getEmail() {
        return email;
    }
}
