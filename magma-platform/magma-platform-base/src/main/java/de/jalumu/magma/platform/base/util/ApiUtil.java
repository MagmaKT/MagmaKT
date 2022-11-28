package de.jalumu.magma.platform.base.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class ApiUtil {

    public static String getResultFromUrl(String url) throws Exception {
        return getResultFromUrl(new URL(url));
    }

    public static String getResultFromUrl(URL url) throws Exception {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = reader.lines().collect(Collectors.joining());
        reader.close();
        connection.disconnect();

        return response;
    }

}
