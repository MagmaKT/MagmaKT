package de.jalumu.magma.platform.bungee.text.notification

import de.exlll.configlib.YamlConfigurationProperties
import de.exlll.configlib.YamlConfigurations
import de.jalumu.magma.platform.base.config.NotificationConfig
import de.jalumu.magma.platform.base.config.serializer.NotificationSerializer
import de.jalumu.magma.platform.bungee.bootstrap.MagmaBungeeBootstrap
import de.jalumu.magma.text.notification.Notification
import de.jalumu.magma.text.notification.NotificationProvider
import java.io.File

class BungeeNotificationProvider(private val magma: MagmaBungeeBootstrap) : NotificationProvider() {
    private var notification: NotificationConfig? = null
    fun init() {
        val notificationFile = File(magma.dataFolder, "notification.yml")
        val configurationProperties = YamlConfigurationProperties
            .newBuilder()
            .addSerializer(Notification::class.java, NotificationSerializer())
            .createParentDirectories(true)
            .build()
        if (notificationFile.exists()) {
            notification = YamlConfigurations.load(
                notificationFile.toPath(),
                NotificationConfig::class.java,
                configurationProperties
            )
        } else {
            notification = NotificationConfig()
            YamlConfigurations.save(
                notificationFile.toPath(),
                NotificationConfig::class.java,
                notification,
                configurationProperties
            )
        }
    }

    override fun getDebug(): Notification {
        return notification!!.debug
    }

    override fun getInfo(): Notification {
        return notification!!.info
    }

    override fun getSuccess(): Notification {
        return notification!!.success
    }

    override fun getWarning(): Notification {
        return notification!!.warning
    }

    override fun getError(): Notification {
        return notification!!.error
    }

    override fun getRaw(template: String, key: String): Notification {
        val notification = BungeeNotification()
        notification.setNotificationTemplate(template)
        notification.setDefaultSound(key)
        return notification
    }
}
