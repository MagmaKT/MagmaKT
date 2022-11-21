package de.jalumu.magma.module;

public @interface ModuleMeta {

        String name();
        String version();
        String author() default "unknown";
        String description() default "No description provided";

        String[] dependsModule() default {};
        String[] dependsPlugin() default {};

}
