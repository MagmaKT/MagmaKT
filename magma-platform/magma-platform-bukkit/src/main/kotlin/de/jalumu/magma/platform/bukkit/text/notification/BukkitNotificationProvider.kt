package de.jalumu.magma.platform.bukkit.text.notification

import de.jalumu.magma.platform.bukkit.bootstrap.MagmaBukkitBootstrap
import de.jalumu.magma.text.notification.Notification
import de.jalumu.magma.text.notification.NotificationProvider
import org.bukkit.Sound
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException

/**
 * The Provider for BukkitNotifications
 */
class BukkitNotificationProvider(private val magma: MagmaBukkitBootstrap) : NotificationProvider() {
    private val configuration: YamlConfiguration

    init {
        val config = File(magma.dataFolder, "notification.yml")
        configuration = YamlConfiguration()
        if (!config.exists()) {
            try {
                config.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        try {
            configuration.load(config)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InvalidConfigurationException) {
            e.printStackTrace()
        }
        configuration.addDefault(
            "notification.global.debug.template",
            "<dark_aqua>DEBUG <dark_gray>| <gray><notification_text>"
        )
        configuration.addDefault("notification.global.debug.sound", Sound.UI_BUTTON_CLICK.key().asString())
        configuration.addDefault(
            "notification.global.info.template",
            "<green>INFO <dark_gray>| <dark_green><notification_text>"
        )
        configuration.addDefault("notification.global.info.sound", Sound.BLOCK_NOTE_BLOCK_CHIME.key().asString())
        configuration.addDefault(
            "notification.global.success.template",
            "<green>SUCCESS <dark_gray>| <green><notification_text>"
        )
        configuration.addDefault("notification.global.success.sound", Sound.ENTITY_PLAYER_LEVELUP.key().asString())
        configuration.addDefault(
            "notification.global.warning.template",
            "<yellow>WARNING <dark_gray>| <green><notification_text>"
        )
        configuration.addDefault("notification.global.warning.sound", Sound.BLOCK_NOTE_BLOCK_BASS.key().asString())
        configuration.addDefault(
            "notification.global.error.template",
            "<red>Error <dark_gray>| <green><notification_text>"
        )
        configuration.addDefault("notification.global.error.sound", Sound.ITEM_SHIELD_BREAK.key().asString())
        configuration.options().copyDefaults(true)
        try {
            configuration.save(config)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun getDebug(): Notification {
        val template = configuration.getString("notification.global.debug.template")!!
        val sound = configuration.getString("notification.global.debug.sound")!!
        val notification = BukkitNotification()
        notification.setNotificationTemplate(template)
        notification.setDefaultSound(sound)
        return notification
    }

    override fun getInfo(): Notification {
        val template = configuration.getString("notification.global.info.template")!!
        val sound = configuration.getString("notification.global.info.sound")!!
        val notification = BukkitNotification()
        notification.setNotificationTemplate(template)
        notification.setDefaultSound(sound)
        return notification
    }

    override fun getSuccess(): Notification {
        val template = configuration.getString("notification.global.success.template")!!
        val sound = configuration.getString("notification.global.success.sound")!!
        val notification = BukkitNotification()
        notification.setNotificationTemplate(template)
        notification.setDefaultSound(sound)
        return notification
    }

    override fun getWarning(): Notification {
        val template = configuration.getString("notification.global.warning.template")!!
        val sound = configuration.getString("notification.global.warning.sound")!!
        val notification = BukkitNotification()
        notification.setNotificationTemplate(template)
        notification.setDefaultSound(sound)
        return notification
    }

    override fun getError(): Notification {
        val template = configuration.getString("notification.global.error.template")!!
        val sound = configuration.getString("notification.global.error.sound")!!
        val notification = BukkitNotification()
        notification.setNotificationTemplate(template)
        notification.setDefaultSound(sound)
        return notification
    }

    override fun getRaw(template: String, key: String): Notification {
        val notification = BukkitNotification()
        notification.setNotificationTemplate(template)
        notification.setDefaultSound(key)
        return notification
    }
}