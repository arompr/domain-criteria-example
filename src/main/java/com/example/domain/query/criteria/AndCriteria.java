package com.example.domain.query.criteria;

public class AndCriteria implements ICriteria {
    private final ICriteria left;
    private final ICriteria right;

    protected AndCriteria(ICriteria left, ICriteria right) {
        this.left = left;
        this.right = right;
    }

    public static ICriteria and(ICriteria left, ICriteria right) {
        return new AndCriteria(left, right);
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
