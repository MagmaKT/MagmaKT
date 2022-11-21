package de.jalumu.magma.module;

import de.jalumu.magma.platform.MagmaPlatform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.logging.Logger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseModule implements MagmaModule {

    private MagmaPlatform platform;
    private File dataFolder;

    private Logger logger;

    private ModuleMeta meta;

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
    public ModuleMeta getMeta() {
        return meta;
    }
}
