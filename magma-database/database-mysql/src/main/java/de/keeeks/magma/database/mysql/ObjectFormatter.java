package de.keeeks.magma.database.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ObjectFormatter<T> {
    T format(ResultSet resultSet) throws SQLException;
}