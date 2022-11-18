package de.keeeks.magma.database.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class MysqlConnection {
    private final HikariDataSource dataSource;

    private MysqlConnection(HikariConfig config) {
        dataSource = new HikariDataSource(config);
    }

    public void prepare(String statement) {
        prepare(statement, PreparedStatementFiller.EMPTY);
    }

    public void prepare(String statement, PreparedStatementFiller preparedStatementFiller) {
        try (Connection connection = connection()) {
            var preparedStatement = connection.prepareStatement(statement);
            preparedStatementFiller.fill(preparedStatement);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T query(String statement, ObjectFormatter<T> objectFormatter) {
        return query(statement, PreparedStatementFiller.EMPTY, objectFormatter);
    }

    public <T> T query(
            String statement,
            PreparedStatementFiller preparedStatementFiller,
            ObjectFormatter<T> objectFormatter
    ) {
        try (Connection connection = connection()) {
            var preparedStatement = connection.prepareStatement(statement);
            preparedStatementFiller.fill(preparedStatement);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return objectFormatter.format(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Connection connection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MysqlConnection create(MysqlCredentials mysqlCredentials) {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://%s:%s/%s".formatted(
                mysqlCredentials.hostname(),
                mysqlCredentials.port(),
                mysqlCredentials.database()
        ));
        hikariConfig.setUsername(mysqlCredentials.username());
        hikariConfig.setPassword(mysqlCredentials.password());
        hikariConfig.setKeepaliveTime(TimeUnit.MINUTES.toMillis(30));
        hikariConfig.setPoolName("magma-sql-pool");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        // TODO: 18.11.2022 implement metrics
        //hikariConfig.setMetricRegistry();

        return new MysqlConnection(hikariConfig);
    }
}