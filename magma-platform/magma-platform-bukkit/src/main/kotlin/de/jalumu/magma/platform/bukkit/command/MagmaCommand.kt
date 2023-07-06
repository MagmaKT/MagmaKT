package de.jalumu.magma.platform.bukkit.command

import de.jalumu.magma.annotation.bukkit.platform.command.Command
import de.jalumu.magma.annotation.bukkit.platform.permission.PermissionDefault
import de.jalumu.magma.platform.bukkit.bootstrap.MagmaBukkitBootstrap
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

@Command(
    name = "magma",
    description = "Magma command",
    permission = "magma.command.magma",
    permissionDefault = PermissionDefault.GRANTED
)
class MagmaCommand(private val magma: MagmaBukkitBootstrap) : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: org.bukkit.command.Command,
        label: String,
        args: Array<String>
    ): Boolean {
        val mm = MiniMessage.miniMessage()
        sender.sendMessage(mm.deserialize("<gray>---------- <gold>MagmaKT-Bukkit <gray>----------"))
        sender.sendMessage(mm.deserialize("<gray>Authors: <green>" + magma.description.authors[0]))
        sender.sendMessage(mm.deserialize("<gray>Version: <green>" + magma.magmaImplementationVersion))
        sender.sendMessage(mm.deserialize("<gray>Source: <green><click:OPEN_URL:https://github.com/JaLuMu/MagmaKT>Github</click>"))
        sender.sendMessage(mm.deserialize("<gray>License: <green>LGPL-3.0"))
        sender.sendMessage(mm.deserialize("<gray>Metrics: <green><click:OPEN_URL:https://bstats.org/plugin/bukkit/MagmaKT-Bukkit/16417>bStats</click>"))
        sender.sendMessage(mm.deserialize("<gray>----------------------------------"))
        return true
    }
}