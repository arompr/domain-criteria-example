package com.example.domain.user.criteria;

import com.example.domain.query.criteria.ICriteriaVisitor;

public interface IUserCriteriaVisitor<R> extends ICriteriaVisitor<R> {
    R visit(EmailEqualsCriteria emailEqualsCriteria);

    R visit(NameContainsCriteria hasNameCriteria);
}
