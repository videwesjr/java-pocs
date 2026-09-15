package com.extra.repository;

public final class RepositoryDemo {

    private RepositoryDemo() {
    }

    public static void run() {
        System.out.println("=== Repository ===");

        UserRepository repository = new InMemoryUserRepository();
        repository.save(new User(1, "Ada Lovelace"));
        repository.save(new User(2, "Grace Hopper"));

        repository.findById(1).ifPresent(user -> System.out.println("Found: " + user));

        repository.deleteById(2);
        System.out.println("After delete, id 2 present? " + repository.findById(2).isPresent());
    }
}
