package de.jalumu.magma.cloud;

import java.util.Set;

public interface ServerGroup {

    String getName();

    ServiceType getServiceType();

    Set<CloudServer> getServers();

}
