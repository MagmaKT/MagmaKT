package de.jalumu.magma.platform.base.module;

import de.jalumu.magma.platform.base.platform.MagmaPlatform;

import java.io.File;

public abstract class ModuleBase implements MagmaModule {

    private MagmaPlatform platform;
    private File dataFolder;

    public ModuleBase(MagmaPlatform platform, File dataFolder) {
        this.platform = platform;
        this.dataFolder = dataFolder;
        dataFolder.mkdir();
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onUnload() {

    }

    @Override
    public MagmaPlatform getPlatform() {
        return platform;
    }

    @Override
    public boolean isCompatible() {
        return false;
    }

    @Override
    public File getDataFolder() {
        return dataFolder;
    }
}
