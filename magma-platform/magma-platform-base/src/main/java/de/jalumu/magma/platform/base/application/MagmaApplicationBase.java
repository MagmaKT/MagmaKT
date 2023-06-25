package de.jalumu.magma.platform.base.application;

import net.kyori.adventure.platform.AudienceProvider;
import revxrsal.commands.CommandHandler;

public interface MagmaApplicationBase {

    void initialize();
    void start();
    void shutdown();
    CommandHandler getCommandHandler();
    AudienceProvider getAudience();

}
