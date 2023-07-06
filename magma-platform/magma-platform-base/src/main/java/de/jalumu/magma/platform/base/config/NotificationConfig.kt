package de.jalumu.magma.platform.base.config

import de.exlll.configlib.Configuration
import de.jalumu.magma.text.notification.Notification
import lombok.Getter

@Configuration
@Getter
class NotificationConfig {
    val debug =
        Notification.raw("<dark_aqua>DEBUG <dark_gray>| <gray><notification_text>", "minecraft:ui.button.click")
    val info =
        Notification.raw("<green>INFO <dark_gray>| <dark_green><notification_text>", "minecraft:block.note_block.chime")
    val success =
        Notification.raw("<green>SUCCESS <dark_gray>| <green><notification_text>", "minecraft:entity.player.levelup")
    val warning =
        Notification.raw("<yellow>WARNING <dark_gray>| <green><notification_text>", "minecraft:block.note_block.bass")
    val error =
        Notification.raw("<red>Error <dark_gray>| <green><notification_text>", "minecraft:item.shield.break")
}
