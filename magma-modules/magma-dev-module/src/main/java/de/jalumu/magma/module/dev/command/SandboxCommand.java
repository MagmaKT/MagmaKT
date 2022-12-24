package de.jalumu.magma.module.dev.command;

import de.jalumu.magma.command.MagmaCommand;
import de.jalumu.magma.module.dev.sandbox.Sandbox;
import de.jalumu.magma.platform.MagmaPlatform;
import de.jalumu.magma.player.Players;
import lombok.AllArgsConstructor;
import revxrsal.commands.command.CommandActor;

@AllArgsConstructor
public class SandboxCommand {

    private MagmaPlatform platform;

    @MagmaCommand(command = {"sandbox", "sbox"}, usage = "sandbox <sandbox_name>", description = "Runs a sandbox", permission = "magma.module.dev.sandbox", autoComplete = "@sandbox *")
    public void sandbox(CommandActor actor, String name) {
        Sandbox.run(name, Players.getOnlinePlayer(actor.getUniqueId()), platform);
    }

}
