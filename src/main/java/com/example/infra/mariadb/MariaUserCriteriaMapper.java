package com.example.infra.mariadb;

import com.example.domain.query.criteria.ICriteria;
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
        }

        throw new UnsupportedOperationException("Unknown spec: " + criteria.getClass());
    }
}
