package de.jalumu.magma.platform.bukkit.bootstrap;

import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurations;
import de.jalumu.magma.annotation.bukkit.platform.application.BukkitPlugin;
import de.jalumu.magma.platform.MagmaPlatformProvider;
import de.jalumu.magma.platform.base.config.ServerIdConfig;
import de.jalumu.magma.platform.MagmaPlatform;
import de.jalumu.magma.platform.MagmaPlatformType;
import de.jalumu.magma.platform.ServerImplementation;
import de.jalumu.magma.platform.base.config.serializer.TextSerializer;
import de.jalumu.magma.platform.base.module.ModuleLoader;
import de.jalumu.magma.platform.base.platform.util.SplashScreen;
import de.jalumu.magma.platform.bukkit.application.BukkitApplication;
import de.jalumu.magma.platform.bukkit.command.MagmaBukkitCommandAnnotationReplacer;
import de.jalumu.magma.platform.bukkit.config.TestConfig;
import de.jalumu.magma.platform.bukkit.module.BukkitModuleLoader;
import de.jalumu.magma.platform.bukkit.player.BukkitPlayerProvider;
import de.jalumu.magma.platform.bukkit.text.BukkitTextProvider;
import de.jalumu.magma.player.PlayerProvider;
import de.jalumu.magma.text.Text;
import de.jalumu.magma.text.TextProvider;
import de.jalumu.magma.text.notification.Notification;
import de.jalumu.magma.text.placeholder.PlaceholderProvider;
import de.jalumu.magma.platform.bukkit.command.MagmaCommand;
import de.jalumu.magma.platform.bukkit.database.BukkitMySQLManager;
import de.jalumu.magma.platform.bukkit.text.notification.BukkitNotificationProvider;
import de.jalumu.magma.platform.bukkit.text.placeholder.BukkitPlaceholderProvider;
import de.jalumu.magma.text.notification.NotificationProvider;
import de.jalumu.magma.util.sandbox.Sandbox;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.CommandHandler;
import revxrsal.commands.bukkit.BukkitCommandHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

@BukkitPlugin(name = "MagmaKT-Bukkit", version = "Dev-Build", description = "MagmaKT for Bukkit", author = "JaLuMu", dependsPlugin = {}, softDependsPlugin = {"ProtocolLib", "LuckPerms"})
public class MagmaBukkitBootstrap extends BukkitApplication implements MagmaPlatform {

    private ModuleLoader moduleLoader;

    private BukkitMySQLManager mySQLManager;

    private CommandHandler commandHandler;

    private ServerIdConfig serverIdConfig;

    private String serverID;

    @Override
    public void initialize() {
        MagmaPlatformProvider.setPlatform(this);
        try {
            Files.createDirectories(Paths.get(this.getDataFolder().toPath() + File.separator + "modules"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        Metrics metrics = new Metrics(this, 16417);

        File serverIdFile = new File(getDataFolder(), "serverID.yml");

        YamlConfigurationProperties configurationProperties = YamlConfigurationProperties.newBuilder().addSerializer(Text.class, new TextSerializer()).createParentDirectories(true).build();

        if (serverIdFile.exists()) {
            serverIdConfig = YamlConfigurations.load(serverIdFile.toPath(), ServerIdConfig.class);
        } else {
            serverIdConfig = new ServerIdConfig("Unknown");
            YamlConfigurations.save(serverIdFile.toPath(), ServerIdConfig.class, serverIdConfig);
        }

        serverID = serverIdConfig.getServerID();

        TextProvider.setTextProvider(new BukkitTextProvider());
        NotificationProvider.setProvider(new BukkitNotificationProvider(this));
        PlaceholderProvider.setProvider(new BukkitPlaceholderProvider(this));
        PlayerProvider.setProvider(new BukkitPlayerProvider(this));

        commandHandler = BukkitCommandHandler.create(this);
        commandHandler.registerAnnotationReplacer(de.jalumu.magma.command.MagmaCommand.class, new MagmaBukkitCommandAnnotationReplacer());

        moduleLoader = new BukkitModuleLoader(this, new File(this.getDataFolder().toPath() + File.separator + "modules"));
        moduleLoader.prepare();

        moduleLoader.loadModules();
        moduleLoader.enableCompatibleModules();
        SplashScreen.splashScreen(this);

        this.getCommand("magma").setExecutor(new MagmaCommand(this));


        mySQLManager = new BukkitMySQLManager(this);

    }

    @Override
    public void shutdown() {
        moduleLoader.disableModules();
        mySQLManager.getDatabase().shutdown();
    }

    @Override
    public String getVersion() {
        return this.getDescription().getVersion();
    }

    @Override
    public MagmaPlatformType getPlatformType() {
        return MagmaPlatformType.GAMESERVER;
    }

    @Override
    public ServerImplementation getServerImplementation() {
        return ServerImplementation.PAPER;
    }

    @Override
    public String getPlatformName() {
        return getServer().getName();
    }

    @Override
    public String getPlatformVersion() {
        return getServer().getVersion();
    }

    @Override
    public String getServerID() {
        return serverID;
    }

    @Override
    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    @Override
    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    @Override
    public Object getMagmaPluginInstance() {
        return this;
    }
}
