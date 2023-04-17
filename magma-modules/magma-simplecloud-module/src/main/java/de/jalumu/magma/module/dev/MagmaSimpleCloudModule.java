package de.jalumu.magma.module.dev;

import de.jalumu.magma.module.BaseModule;
import de.jalumu.magma.module.ModuleMeta;
import de.jalumu.magma.platform.MagmaPlatform;
import de.jalumu.magma.platform.MagmaPlatformType;
import de.jalumu.magma.platform.ServerImplementation;
import eu.thesimplecloud.plugin.startup.CloudPlugin;

@ModuleMeta(
        name = "MagmaSimpleCloudModule",
        version = "1.0",
        author = "Jalumu",
        description = "SimpleCloud Support",
        supportedPlatforms = {MagmaPlatformType.GAMESERVER, MagmaPlatformType.PROXY},
        supportedServerImplementations = {ServerImplementation.PAPER, ServerImplementation.BUNGEECORD}
)
public class MagmaSimpleCloudModule extends BaseModule {

    @Override
    public void onEnable() {
        String serverID = CloudPlugin.getInstance().getThisServiceName();
        MagmaPlatform.getPlatform().setServerID(serverID);
    }
}
