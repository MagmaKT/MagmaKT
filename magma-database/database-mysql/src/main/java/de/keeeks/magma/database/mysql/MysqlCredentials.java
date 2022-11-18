package de.keeeks.magma.database.mysql;

import de.keeeks.magma.configuration.api.Configuration;

public record MysqlCredentials(
        String hostname,
        int port,
        String username,
        String password,
        String database
) {

    public static MysqlCredentials createFromConfiguration(Configuration configuration) {
        return configuration.readObject(MysqlCredentials.class, MysqlCredentials.create(
                "127.0.0.1",
                3306,
                "username",
                "password",
                "database"
        ));
    }

    public static MysqlCredentials create(
            String hostname,
            int port,
            String username,
            String password,
            String database
    ) {
        return new MysqlCredentials(hostname, port, username, password, database);
    }
}