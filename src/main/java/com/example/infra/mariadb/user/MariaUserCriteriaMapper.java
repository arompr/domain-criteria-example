package com.example.infra.mariadb.user;

import com.example.domain.query.criteria.ICriteria;
import com.example.domain.user.criteria.EmailEqualsCriteria;
import com.example.domain.user.criteria.NameContainsCriteria;
import com.example.domain.user.criteria.IUserCriteriaVisitor;
import com.example.infra.mariadb.query.MariaCriteriaMapper;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class MariaUserCriteriaMapper extends MariaCriteriaMapper implements IUserCriteriaVisitor<Predicate> {
    private final Root<MariaUser> root;
    private final CriteriaBuilder cb;

    public MariaUserCriteriaMapper(Root<MariaUser> root, CriteriaBuilder cb) {
        super(cb);
        this.root = root;
        this.cb = cb;
    }

    public Predicate toPredicate(ICriteria criteria) {
        return criteria.accept(this);
    }

    @Override
    public Predicate visit(EmailEqualsCriteria emailEqualsCriteria) {
        return cb.equal(root.get("email"), emailEqualsCriteria.getEmail());
    }

    @Override
    public Predicate visit(NameContainsCriteria nameContainsCriteria) {
        return cb.like(root.get("firstName"), String.format("%s%s%s", "%", nameContainsCriteria.getName(), "%"));
    }
}
