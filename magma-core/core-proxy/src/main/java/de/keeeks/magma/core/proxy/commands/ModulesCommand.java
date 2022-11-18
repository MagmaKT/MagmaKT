package de.keeeks.magma.core.proxy.commands;

import de.keeeks.magma.core.api.Module;
import de.keeeks.magma.core.api.ModuleLoader;
import de.keeeks.magma.core.proxy.MagmaKTProxyPlugin;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.event.HoverEventSource;
import net.kyori.adventure.text.format.TextColor;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bungee.core.BungeeActor;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ModulesCommand {
    private final ModuleLoader moduleLoader;


    @Command({"modules", "module"})
    public void execute(BungeeActor commandActor) {
        var textComponent = Component.text("Modules (%s): ".formatted(
                moduleLoader.initialized().size()
        ));

        List<Component> components = new LinkedList<>();
        for (Module module : moduleLoader.initialized()) {
            var moduleComponent = Component.text(module.description().name())
                    .hoverEvent((HoverEventSource<Component>) op -> Component.text(module.description().description())
                            .color(TextColor.fromHexString("#00ff44"))
                            .asHoverEvent())
                    .color(TextColor.fromHexString(hexColorForModule(module)));
            components.add(moduleComponent);
        }

        MagmaKTProxyPlugin
                .plugin()
                .adventure()
                .sender(commandActor.getSender())
                .sendMessage(textComponent.append(
                        Component.join(JoinConfiguration.commas(true), components)
                ));
    }

    private String hexColorForModule(Module module) {
        return moduleEnabled(module) ? "#00ff44" : "#ff0000";
    }

    private boolean moduleEnabled(Module module) {
        return moduleLoader.enabled().contains(module);
    }

    public static ModulesCommand create(ModuleLoader moduleLoader) {
        return new ModulesCommand(moduleLoader);
    }
}