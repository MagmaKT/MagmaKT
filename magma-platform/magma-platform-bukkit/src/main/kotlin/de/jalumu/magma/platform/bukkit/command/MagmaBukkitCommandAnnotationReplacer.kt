package de.jalumu.magma.platform.bukkit.command

import de.jalumu.magma.command.MagmaCommand
import revxrsal.commands.annotation.AutoComplete
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Description
import revxrsal.commands.annotation.Usage
import revxrsal.commands.annotation.dynamic.AnnotationReplacer
import revxrsal.commands.annotation.dynamic.Annotations
import revxrsal.commands.bukkit.annotation.CommandPermission
import java.lang.reflect.AnnotatedElement
import java.util.*

/**
 * An Annotation Replacer to translate MagmaCommands to Lamp
 */
class MagmaBukkitCommandAnnotationReplacer : AnnotationReplacer<MagmaCommand?> {
    override fun replaceAnnotations(element: AnnotatedElement, annotation: MagmaCommand): Collection<Annotation>? {
        println("Translating " + annotation.command)
        val command = Annotations.create(
            Command::class.java, "value", annotation.command
        )
        val usage = Annotations.create(
            Usage::class.java, "value", annotation.usage
        )
        val description = Annotations.create(
            Description::class.java, "value", annotation.description
        )
        val permission = Annotations.create(
            CommandPermission::class.java, "value", annotation.permission
        )
        if (annotation.autoComplete != "") {
            val autoComplete = Annotations.create(
                AutoComplete::class.java, "value", annotation.autoComplete
            )
            return Arrays.asList(
                command,
                usage,
                description,
                permission,
                autoComplete
            )
        }
        return Arrays.asList(
            command,
            usage,
            description,
            permission
        )
    }
}