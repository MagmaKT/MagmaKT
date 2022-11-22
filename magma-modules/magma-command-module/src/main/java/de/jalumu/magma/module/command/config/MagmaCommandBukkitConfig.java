package de.jalumu.magma.module.command.config;

import de.exlll.configlib.Comment;
import lombok.Getter;

@Getter
public class MagmaCommandBukkitConfig extends MagmaCommandConfig {

    @Comment("If enabled, only commands on the whitelist will be allowed")
    private boolean whitelistEnabled = false;

    @Comment("Will be send to the player when he tries to use a command which is not in the whitelist")
    private String commandNotFoundMessage = "<red>Command not found!";
}
