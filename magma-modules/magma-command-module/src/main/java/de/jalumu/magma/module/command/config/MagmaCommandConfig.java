package de.jalumu.magma.module.command.config;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import lombok.Getter;

import java.util.List;

@Configuration
@Getter
public class MagmaCommandConfig {

    @Comment({"A list of commands send to the player when he joins the server",
            "This list is used for command tab completion"})
    private List<String> allowedCommands = List.of(
            "friends", "friend", "party", "clan", "clans",
            "hub", "lobby", "spawn",
            "magma");

}
