package de.jalumu.magma.module.dev;

import de.jalumu.magma.module.BaseModule;
import de.jalumu.magma.module.ModuleMeta;
import de.jalumu.magma.module.dev.command.SandboxCommand;
import de.jalumu.magma.util.sandbox.Sandbox;
import de.jalumu.magma.platform.MagmaPlatformType;
import de.jalumu.magma.platform.ServerImplementation;
import net.kyori.adventure.text.Component;

@ModuleMeta(
        name = "MagmaDevModule",
        version = "1.0",
        author = "Jalumu",
        description = "Developer tools for Magma",
        supportedPlatforms = MagmaPlatformType.GAMESERVER,
        supportedServerImplementations = ServerImplementation.PAPER
)
public class MagmaDevModule extends BaseModule {

    @Override
    public void onEnable() {
        getPlatform().getCommandHandler().getAutoCompleter().registerSuggestion("sandbox",(args, sender, command) -> Sandbox.getKeys());
        getPlatform().getCommandHandler().register(new SandboxCommand(getPlatform()));
    }
}
