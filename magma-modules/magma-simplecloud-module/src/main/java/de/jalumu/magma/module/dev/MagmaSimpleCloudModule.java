package de.jalumu.magma.module.dev;

import de.jalumu.magma.cloud.*;
import de.jalumu.magma.module.BaseModule;
import de.jalumu.magma.module.ModuleMeta;
import de.jalumu.magma.platform.MagmaPlatform;
import de.jalumu.magma.platform.MagmaPlatformType;
import de.jalumu.magma.platform.ServerImplementation;
import de.jalumu.magma.text.Text;
import de.jalumu.magma.util.sandbox.Sandbox;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.plugin.startup.CloudPlugin;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

@ModuleMeta(
        name = "MagmaSimpleCloudModule",
        version = "1.0",
        author = "Jalumu",
        description = "SimpleCloud Support",
        supportedPlatforms = {MagmaPlatformType.GAMESERVER, MagmaPlatformType.PROXY},
        supportedServerImplementations = {ServerImplementation.PAPER, ServerImplementation.BUNGEECORD}
)
public class MagmaSimpleCloudModule extends BaseModule implements Cloud {

    @Override
    public void onEnable() {
        String serverID = CloudPlugin.getInstance().getThisServiceName();
        MagmaPlatform.getPlatform().setServerID(serverID);
        CloudProvider.setCloud(this);

        Sandbox.register("cloud", (player, platform) -> {
            player.getAudience().sendMessage(Text.text("OK").getDefaultComponent());
            for (ServerGroup group : this.getServerGroups()) {
                player.getAudience().sendMessage(Text.text(group.getName()).getDefaultComponent());
                player.getAudience().sendMessage(Text.text("Services").getDefaultComponent());
                for (CloudServer server : group.getServers()) {
                    player.getAudience().sendMessage(Text.text(server.getName()).getDefaultComponent());
                    player.getAudience().sendMessage(Text.text("- " + server.getAddress()).getDefaultComponent());
                    player.getAudience().sendMessage(Text.text("- " + server.getOnlinePlayers() + "/" + server.getMaxPlayers()).getDefaultComponent());
                }
            }
        });

    }

    @Override
    public String getName() {
        return "SimpleCloud";
    }

    @Override
    public Set<ServerGroup> getServerGroups() {
        System.out.println(CloudAPI.getInstance().getCloudServiceGroupManager().getLobbyGroups());
        HashSet<ServerGroup> groups = new HashSet<>();
        CloudAPI.getInstance().getCloudServiceGroupManager().getLobbyGroups().forEach(iCloudServerGroup -> {

            System.out.println(iCloudServerGroup.getName());
            groups.add(new ServerGroup() {
                @Override
                public String getName() {
                    return iCloudServerGroup.getName();
                }

                @Override
                public ServiceType getServiceType() {
                    return switch (iCloudServerGroup.getServiceType()) {
                        case PROXY -> ServiceType.PROXY;
                        case SERVER -> ServiceType.SERVER;
                        case LOBBY -> ServiceType.LOBBY;
                    };
                }

                @Override
                public Set<CloudServer> getServers() {
                    HashSet<CloudServer> servers = new HashSet<>();
                    CloudAPI.getInstance().getCloudServiceManager().getCloudServicesByGroupName(iCloudServerGroup.getName()).forEach(iCloudService -> {
                        servers.add(new CloudServer() {
                            @Override
                            public String getName() {
                                return iCloudService.getName();
                            }

                            @Override
                            public InetAddress getAddress() {
                                return new InetSocketAddress(iCloudService.getHost(), iCloudService.getPort()).getAddress();
                            }

                            @Override
                            public int getMaxPlayers() {
                                return iCloudServerGroup.getMaxPlayers();
                            }

                            @Override
                            public int getOnlinePlayers() {
                                return iCloudService.getOnlineCount();
                            }

                            @Override
                            public boolean isOnline() {
                                return iCloudService.isOnline();
                            }

                            @Override
                            public boolean supportsReboots() {
                                return false;
                            }

                            @Override
                            public void start() {

                            }

                            @Override
                            public void shutdown() {
                                iCloudService.shutdown();
                            }
                        });
                    });
                    return servers;
                }
            });
        });
        return groups;
    }

    @Override
    public ServerGroup getServerGroup(String name) {
        return null;
    }
}
