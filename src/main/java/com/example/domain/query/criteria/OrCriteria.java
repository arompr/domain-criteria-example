package com.example.domain.query.criteria;

public class OrCriteria implements ICriteria {
    private final ICriteria left;
    private final ICriteria right;

    public OrCriteria(ICriteria left, ICriteria right) {
        this.left = left;
        this.right = right;
    }

    public ICriteria getLeft() {
        return left;
    }

    public ICriteria getRight() {
        return right;
    }

    @Override
    public <R> R accept(ICriteriaVisitor<R> criteriaVisitor) {
        return criteriaVisitor.visit(this);
    }
}
