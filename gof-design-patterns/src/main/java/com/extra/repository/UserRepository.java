package com.extra.repository;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(long id);

    void deleteById(long id);
}
