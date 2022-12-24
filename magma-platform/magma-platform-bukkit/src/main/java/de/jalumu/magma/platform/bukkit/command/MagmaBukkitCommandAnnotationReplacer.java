package de.jalumu.magma.platform.bukkit.command;


import de.jalumu.magma.command.MagmaCommand;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.annotation.AutoComplete;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Description;
import revxrsal.commands.annotation.Usage;
import revxrsal.commands.annotation.dynamic.AnnotationReplacer;
import revxrsal.commands.annotation.dynamic.Annotations;
import revxrsal.commands.bukkit.annotation.CommandPermission;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Collection;

public class MagmaBukkitCommandAnnotationReplacer implements AnnotationReplacer<MagmaCommand> {

    @Override
    public @Nullable
    Collection<Annotation> replaceAnnotations(@NotNull AnnotatedElement element, @NotNull MagmaCommand annotation) {
        System.out.println("Translating " + annotation.command());
        Command command = Annotations.create(Command.class, "value", annotation.command());
        Usage usage = Annotations.create(Usage.class, "value", annotation.usage());
        Description description = Annotations.create(Description.class, "value", annotation.description());
        CommandPermission permission = Annotations.create(CommandPermission.class, "value", annotation.permission());

        if (!annotation.autoComplete().equals("")) {
            AutoComplete autoComplete = Annotations.create(AutoComplete.class, "value", annotation.autoComplete());
            return Arrays.asList(
                    command,
                    usage,
                    description,
                    permission,
                    autoComplete
            );
        }

        return Arrays.asList(
                command,
                usage,
                description,
                permission
        );
    }

}
