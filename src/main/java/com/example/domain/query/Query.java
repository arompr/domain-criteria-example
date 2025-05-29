package com.example.domain.query;

import com.example.domain.query.criteria.ICriteria;

public class Query {
    private final ICriteria criteria;

    public Query(ICriteria criteria) {
        this.criteria = criteria;
    }

    public static Query where(ICriteria criteria) {
        return new Query(criteria);
    }

    public ICriteria getCriteria() {
        return criteria;
    }
}
