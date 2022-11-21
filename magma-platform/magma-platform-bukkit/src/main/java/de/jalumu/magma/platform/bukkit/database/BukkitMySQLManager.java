package de.jalumu.magma.platform.bukkit.database;

import de.jalumu.magma.platform.base.database.mysql.MySQLDatabase;
import de.jalumu.magma.platform.bukkit.bootstrap.MagmaBukkitBootstrap;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BukkitMySQLManager {

    private MySQLDatabase database;

    private File config;
    private YamlConfiguration configuration;

    public BukkitMySQLManager(MagmaBukkitBootstrap bukkitBootstrap) {

        config = new File(bukkitBootstrap.getDataFolder(), "mysql.yml");
        configuration = new YamlConfiguration();

        if (!config.exists()) {
            try {
                config.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            configuration.load(config);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        configuration.addDefault("mysql.enabled", false);
        configuration.addDefault("mysql.host", "localhost");
        configuration.addDefault("mysql.port", 3306);
        configuration.addDefault("mysql.database", "magma");
        configuration.addDefault("mysql.user", "root");
        configuration.addDefault("mysql.password", "password");

        configuration.options().copyDefaults(true);

        try {
            configuration.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (configuration.getBoolean("mysql.enabled")) {
            database = new MySQLDatabase(
                    configuration.getString("mysql.host"),
                    configuration.getInt("mysql.port"),
                    configuration.getString("mysql.database"),
                    configuration.getString("mysql.user"),
                    configuration.getString("mysql.password")
            );
        } else {
            Bukkit.getLogger().severe("MySQL is not configured!");
            Bukkit.getPluginManager().disablePlugin(bukkitBootstrap);
        }
    }

    public MySQLDatabase getDatabase() {
        return database;
    }

}
