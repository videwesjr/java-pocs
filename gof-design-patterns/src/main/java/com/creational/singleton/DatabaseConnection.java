package com.creational.singleton;

public final class DatabaseConnection {

    private static volatile DatabaseConnection instance;

    private final String connectionId;

    private DatabaseConnection() {
        connectionId = "conn-" + System.nanoTime();
    }

    public static DatabaseConnection getInstance() {
        DatabaseConnection result = instance;
        if (result == null) {
            synchronized (DatabaseConnection.class) {
                result = instance;
                if (result == null) {
                    instance = result = new DatabaseConnection();
                }
            }
        }
        return result;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void query(String sql) {
        System.out.println("[" + connectionId + "] executing: " + sql);
    }
}
