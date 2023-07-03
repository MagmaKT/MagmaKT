package de.jalumu.magma.platform.bungee.command

import de.jalumu.magma.command.MagmaCommand
import de.jalumu.magma.player.MagmaPlayer
import de.jalumu.magma.text.notification.Notification

class TestCommand {
    @MagmaCommand(command = ["Test"], description = "", usage = "test", permission = "test")
    fun test(player: MagmaPlayer?) {
        Notification.info("Test")
    }
}
