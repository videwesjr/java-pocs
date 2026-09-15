package com.extra.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryUserRepository implements UserRepository {

    private final Map<Long, User> storage = new ConcurrentHashMap<>();

    @Override
    public User save(User user) {
        storage.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteById(long id) {
        storage.remove(id);
    }
}
