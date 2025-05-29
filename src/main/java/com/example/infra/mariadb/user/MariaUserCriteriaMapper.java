package com.example.infra.mariadb.user;

import com.example.domain.query.criteria.AndCriteria;
import com.example.domain.query.criteria.ICriteria;
import com.example.domain.query.criteria.NotCriteria;
import com.example.domain.query.criteria.OrCriteria;
import com.example.domain.user.criteria.EmailEqualsCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class MariaUserCriteriaMapper {
    private final Root<MariaUser> root;
    private final CriteriaBuilder cb;

    public MariaUserCriteriaMapper(Root<MariaUser> root, CriteriaBuilder cb) {
        this.root = root;
        this.cb = cb;
    }

    public Predicate toPredicate(ICriteria criteria) {
        if (criteria instanceof EmailEqualsCriteria) {
            return cb.equal(root.get("email"), ((EmailEqualsCriteria) criteria).getEmail());
        } else if (criteria instanceof AndCriteria) {
            Predicate left = toPredicate(((AndCriteria) criteria).getLeft());
            Predicate right = toPredicate(((AndCriteria) criteria).getRight());
            return cb.and(left, right);
        } else if (criteria instanceof OrCriteria) {
            Predicate left = toPredicate(((OrCriteria) criteria).getLeft());
            Predicate right = toPredicate(((OrCriteria) criteria).getRight());
            return cb.or(left, right);
        } else if (criteria instanceof NotCriteria) {
            return cb.not(toPredicate(((NotCriteria) criteria).getCriteria()));
        }

        throw new UnsupportedOperationException("Unknown spec: " + criteria.getClass());
    }
}
