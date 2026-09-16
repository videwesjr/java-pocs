package com.extra.objectpool;

public final class ObjectPoolDemo {

    private ObjectPoolDemo() {
    }

    public static void run() {
        System.out.println("=== Object Pool ===");

        ConnectionPool pool = new ConnectionPool(2);

        PooledConnection first = pool.acquire();
        PooledConnection second = pool.acquire();
        first.query("SELECT 1");
        second.query("SELECT 2");

        pool.release(first);

        PooledConnection reused = pool.acquire();
        reused.query("SELECT 3");

        System.out.println("Reused same connection? " + (first == reused));
        System.out.println("Total connections created: " + pool.getCreatedCount());
    }
}
