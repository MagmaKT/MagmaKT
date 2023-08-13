package de.jalumu.magma.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An Annotation for Magma commands
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface MagmaCommand {

    String[] command();

    String description() default "A Magma Command";

    String usage();

    String permission();

    String autoComplete() default "";

}
