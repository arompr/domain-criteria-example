package com.example.domain.user.criteria;

import com.example.domain.query.criteria.ICriteria;

public class HasNameCriteria implements ICriteria {
    private final String name;

    private HasNameCriteria(String name) {
        this.name = name;
    }

    public static HasNameCriteria hasName(String name) {
        return new HasNameCriteria(name);
    }

    public String getName() {
        return name;
    }
}
