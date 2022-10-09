package de.jalumu.magma.annotation.platform.bukkit.plugin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BukkitApiVersion {

    V1_8("1.8"),
    V1_12("1.12"),
    V1_16("1.16"),
    V1_17("1.17"),
    V1_18("1.18"),
    V1_19("1.19");

    @Getter
    String apiVersion;

}
