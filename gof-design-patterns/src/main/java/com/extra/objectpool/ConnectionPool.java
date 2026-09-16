package com.extra.objectpool;

import java.util.ArrayDeque;
import java.util.Deque;

public final class ConnectionPool {

    private final Deque<PooledConnection> available = new ArrayDeque<>();
    private int createdCount;

    public ConnectionPool(int initialSize) {
        for (int i = 0; i < initialSize; i++) {
            available.push(createConnection());
        }
    }

    private PooledConnection createConnection() {
        createdCount++;
        return new PooledConnection("conn-" + createdCount);
    }

    public PooledConnection acquire() {
        return available.isEmpty() ? createConnection() : available.pop();
    }

    public void release(PooledConnection connection) {
        available.push(connection);
    }

    public int getCreatedCount() {
        return createdCount;
    }
}
