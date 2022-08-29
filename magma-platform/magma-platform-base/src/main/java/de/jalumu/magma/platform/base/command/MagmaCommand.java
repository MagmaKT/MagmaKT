package de.jalumu.magma.platform.base.command;

import java.util.Set;

public interface MagmaCommand {

    boolean onCommand();
    Set<String> onTabComplete();

}
