package de.keeeks.magma.chat.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.core.BukkitActor;

public class TestCommand {

    @Command("test")
    public void test(BukkitActor bukkitActor) {
        bukkitActor.reply(Component.text("Test!").color(TextColor.fromHexString("#8515FF")));
    }
}