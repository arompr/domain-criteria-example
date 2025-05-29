package com.example.domain.user;

import com.example.domain.query.Query;

import java.util.List;

public interface IUserRepository {
    void save(User user);

    List<User> fetch(Query query);
}
