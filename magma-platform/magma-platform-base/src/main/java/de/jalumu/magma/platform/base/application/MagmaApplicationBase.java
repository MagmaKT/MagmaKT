package de.jalumu.magma.platform.base.application;

import revxrsal.commands.CommandHandler;

public interface MagmaApplicationBase {

    void initialize();
    void start();
    void shutdown();
    CommandHandler getCommandHandler();

}
