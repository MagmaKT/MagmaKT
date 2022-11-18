package de.keeeks.magma.database.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementFiller {
    PreparedStatementFiller EMPTY = preparedStatement -> {
    };

    void fill(PreparedStatement preparedStatement) throws SQLException;
}