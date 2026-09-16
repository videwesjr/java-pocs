package com.extra.objectpool;

public final class PooledConnection {

    private final String id;

    public PooledConnection(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void query(String sql) {
        System.out.println("[" + id + "] executing: " + sql);
    }
}
