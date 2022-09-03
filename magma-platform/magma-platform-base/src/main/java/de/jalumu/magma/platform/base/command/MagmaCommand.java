package de.jalumu.magma.platform.base.command;

import java.util.Set;

public interface MagmaCommand {

    boolean onCommand(MagmaCommandSender sender, String command, String[] args);

    Set<String> onTabComplete();

}
