package de.jalumu.magma.platform.base.config;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ServerIdConfig {
    @Comment("A unique Identifier for the current server Instance")
    private String serverID;

}
