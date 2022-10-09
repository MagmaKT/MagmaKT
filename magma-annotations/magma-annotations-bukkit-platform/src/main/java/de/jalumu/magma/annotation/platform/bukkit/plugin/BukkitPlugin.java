package de.jalumu.magma.annotation.platform.bukkit.plugin;

import de.jalumu.magma.annotation.bukkit.platform.application.BukkitApiVersion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * The BukkitPlugin Annotation helps with the creation of a Bukkit plugin.yml file
 *
 * @author Jalumu
 */
@Target(ElementType.TYPE)
public @interface BukkitPlugin {

    /**
     * The name of the plugin
     *
     * @return The name of the plugin
     */
    String name();

    /**
     * The version of the plugin
     *
     * @return The version of the plugin
     */
    String version();

    /**
     * The description of the plugin
     *
     * @return The description of the plugin
     */
    String description() default "A Magma-Based Plugin";

    /**
     * The api version of the plugin
     *
     * @return The api version of the plugin
     */
    BukkitApiVersion apiVersion() default BukkitApiVersion.V1_19;

    /**
     * The ApplicationStartProperty of the plugin
     *
     * @return The ApplicationStartProperty of the plugin
     */
    BukkitApplicationStartProperty loadOn() default BukkitApplicationStartProperty.POSTWORLD;

    /**
     * The main author of the plugin
     *
     * @return The main author of the plugin
     */
    String author();

    /**
     * The authors of the plugin
     *
     * @return The authors of the plugin
     */
    String[] authors() default {};

    /**
     * The website of the plugin
     *
     * @return The website of the plugin
     */
    String website() default "";

    /**
     * The prefix of the plugin
     *
     * @return The prefix of the plugin
     */
    String prefix() default "";

    /**
     * The dependencies of the plugin
     *
     * @return The dependencies of the plugin
     */
    String[] depend() default {};

    /**
     * The soft dependencies of the plugin
     *
     * @return The soft dependencies of the plugin
     */
    String[] softDepend() default {};

    /**
     * All plugins that should be loaded after this plugin
     *
     * @return All plugins that should be loaded after this plugin
     */
    String[] loadBefore() default {};

}
