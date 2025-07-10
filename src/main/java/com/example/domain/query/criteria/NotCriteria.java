package com.example.domain.query.criteria;

public class NotCriteria implements ICriteria {
    private final ICriteria criteria;

    public NotCriteria(ICriteria criteria) {
        this.criteria = criteria;
    }

    public ICriteria getCriteria() {
        return this.criteria;
    }

    @Override
    public <R> R accept(ICriteriaVisitor<R> criteriaVisitor) {
        return criteriaVisitor.visit(this);
    }
}
