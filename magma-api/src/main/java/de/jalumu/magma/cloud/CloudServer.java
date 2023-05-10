package de.jalumu.magma.cloud;

import java.net.InetAddress;

public interface CloudServer {

    String getName();

    InetAddress getAddress();

    int getMaxPlayers();

    int getOnlinePlayers();

    boolean isOnline();

    boolean supportsReboots();

    void start();

    void shutdown();



}
