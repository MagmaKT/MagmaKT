package de.jalumu.magma.platform.base.database.mysql

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

class MySQLDatabase(host: String, port: Int, database: String, user: String?, password: String?) {
    val dataSource: HikariDataSource

    init {
        val config = HikariConfig()
        config.jdbcUrl = "jdbc:mysql://$host:$port/$database"
        config.username = user
        config.password = password
        config.poolName = "MagmaMySQLPool"
        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "250")
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
        dataSource = HikariDataSource(config)
    }

    fun shutdown() {
        if (dataSource.isRunning) {
            dataSource.close()
        }
    }
}
