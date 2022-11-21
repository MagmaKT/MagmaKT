package de.jalumu.magma.module;

import de.jalumu.magma.platform.MagmaPlatformType;
import de.jalumu.magma.platform.ServerImplementation;

public @interface ModuleMeta {

        String name();
        String version();
        String author() default "unknown";
        String description() default "No description provided";

        MagmaPlatformType[] supportedPlatforms();
        ServerImplementation[] supportedServerImplementations() default {};

        String[] dependsModule() default {};
        String[] dependsPlugin() default {};

}
