package de.jalumu.magma.cloud;

import java.util.Set;

public interface Cloud {

    static boolean isAvailable() {
        return CloudProvider.isAvailable;
    }

    static Cloud getCloud() {
        return CloudProvider.getCloud();
    }

    String getName();

    Set<ServerGroup> getServerGroups();

    ServerGroup getServerGroup(String name);

}
