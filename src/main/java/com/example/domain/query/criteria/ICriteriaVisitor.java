package com.example.domain.query.criteria;

public interface ICriteriaVisitor<R> {
    R visit(AndCriteria spec);

    R visit(OrCriteria spec);

    R visit(NotCriteria spec);
}
