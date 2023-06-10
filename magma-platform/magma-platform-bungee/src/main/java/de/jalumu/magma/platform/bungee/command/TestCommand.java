package de.jalumu.magma.platform.bungee.command;

import de.jalumu.magma.command.MagmaCommand;
import de.jalumu.magma.player.MagmaPlayer;
import de.jalumu.magma.text.notification.Notification;

public class TestCommand {

    @MagmaCommand(command = "Test", description = "", usage = "test", permission = "test")
    public void test(MagmaPlayer player){
        Notification.info("Test");
    }

}
