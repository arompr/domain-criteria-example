package com.example.domain.query.criteria;

public interface ICriteria {
    default ICriteria and(ICriteria other) {
        return new AndCriteria(this, other);
    }

    default ICriteria or(ICriteria other) {
        return new OrCriteria(this, other);
    }

    default ICriteria not() {
        return new NotCriteria(this);
    }
}
