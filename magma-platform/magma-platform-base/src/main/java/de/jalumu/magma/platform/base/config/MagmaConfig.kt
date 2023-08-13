package de.jalumu.magma.platform.base.config

import java.io.File

interface MagmaConfig {
    fun read()
    fun read(file: File?)
    fun setObject(key: String?, o: Any?)
    fun setString(key: String?, s: String?)
    fun setInt(key: String?, i: Int)
    fun save()
    fun save(file: File?)
}
