package de.jalumu.magma.platform.base.player;

import de.jalumu.magma.platform.base.command.MagmaCommandSender;
import net.kyori.adventure.text.Component;

public interface MagmaPlayer extends MagmaCommandSender {

    String getName();
    String getUUID();

    void sendMessage(Component component);

}
