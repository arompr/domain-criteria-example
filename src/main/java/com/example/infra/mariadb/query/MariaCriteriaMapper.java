package com.example.infra.mariadb.query;

import com.example.domain.query.criteria.AndCriteria;
import com.example.domain.query.criteria.ICriteriaVisitor;
import com.example.domain.query.criteria.NotCriteria;
import com.example.domain.query.criteria.OrCriteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;

public class MariaCriteriaMapper implements ICriteriaVisitor<Predicate> {
    private final CriteriaBuilder cb;

    public MariaCriteriaMapper(CriteriaBuilder cb) {
        this.cb = cb;
    }

    public Predicate visit(AndCriteria spec) {
        return cb.and(spec.getLeft().accept(this), spec.getRight().accept(this));
    }

    public Predicate visit(OrCriteria spec) {
        return cb.or(spec.getLeft().accept(this), spec.getRight().accept(this));
    }

    public Predicate visit(NotCriteria spec) {
        return cb.not(spec.getCriteria().accept(this));
    }
}
