package de.jalumu.magma.platform.bukkit.command;

import de.jalumu.magma.annotation.bukkit.platform.permission.PermissionDefault;
import de.jalumu.magma.platform.bukkit.bootstrap.MagmaBukkitBootstrap;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@de.jalumu.magma.annotation.bukkit.platform.command.Command(name = "magma", description = "Magma command", permission = "magma.command.magma", permissionDefault = PermissionDefault.GRANTED)
public class MagmaCommand implements CommandExecutor {

    private MagmaBukkitBootstrap magma;

    public MagmaCommand(MagmaBukkitBootstrap magma) {
        this.magma = magma;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        var mm = MiniMessage.miniMessage();
        sender.sendMessage(mm.deserialize("<gray>---------- <gold>MagmaKT-Bukkit <gray>----------"));
        sender.sendMessage(mm.deserialize("<gray>Authors: <green>" + magma.getDescription().getAuthors().get(0)));
        sender.sendMessage(mm.deserialize("<gray>Version: <green>" + magma.getVersion()));
        sender.sendMessage(mm.deserialize("<gray>Source: <green><click:OPEN_URL:https://github.com/JaLuMu/MagmaKT>Github</click>"));
        sender.sendMessage(mm.deserialize("<gray>License: <green>LGPL-3.0"));
        sender.sendMessage(mm.deserialize("<gray>Metrics: <green><click:OPEN_URL:https://bstats.org/plugin/bukkit/MagmaKT-Bukkit/16417>bStats</click>"));
        sender.sendMessage(mm.deserialize("<gray>----------------------------------"));
        return true;
    }
}
