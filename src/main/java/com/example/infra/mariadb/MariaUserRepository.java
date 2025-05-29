package com.example.infra.mariadb;

import com.example.domain.query.Query;
import com.example.domain.user.IUserRepository;
import com.example.domain.user.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.UUID;

public class MariaUserRepository implements IUserRepository, PanacheRepositoryBase<MariaUser, UUID> {
    @Override
    public void save(User user) {
        persist(MariaUserMapper.toDto(user));
    }

    @Override
    public List<User> fetch(Query query) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MariaUser> criteriaQuery = criteriaBuilder.createQuery(MariaUser.class);
        Root<MariaUser> root = criteriaQuery.from(MariaUser.class);

        MariaUserCriteriaMapper criteriaMapper = new MariaUserCriteriaMapper(root, criteriaBuilder);
        Predicate predicate = criteriaMapper.toPredicate(query.getCriteria());
        criteriaQuery.where(predicate);

        return getEntityManager().createQuery(criteriaQuery).getResultList().stream()
                .map(MariaUserMapper::toEntity)
                .toList();
    }
}
