package de.jalumu.magma.platform.base.util

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors

object ApiUtil {
    @Throws(Exception::class)
    fun getResultFromUrl(url: String?): String {
        return getResultFromUrl(URL(url))
    }

    @Throws(Exception::class)
    fun getResultFromUrl(url: URL): String {
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")
        val reader = BufferedReader(InputStreamReader(connection.inputStream))
        val response = reader.lines().collect(Collectors.joining())
        reader.close()
        connection.disconnect()
        return response
    }
}
