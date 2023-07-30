package de.jalumu.magma.platform.base.config;

import de.exlll.configlib.Configuration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServerIdConfig {
    private String serverID;
}
