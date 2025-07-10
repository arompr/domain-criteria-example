package com.example.domain.user.criteria;

import com.example.domain.query.criteria.ICriteria;
import com.example.domain.query.criteria.ICriteriaVisitor;

public class NameContainsCriteria implements ICriteria {
    private final String name;

    private NameContainsCriteria(String name) {
        this.name = name;
    }

    public static NameContainsCriteria hasName(String name) {
        return new NameContainsCriteria(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public <R> R accept(ICriteriaVisitor<R> criteriaVisitor) {
        return ((IUserCriteriaVisitor<R>) criteriaVisitor).visit(this);
    }
}
