package de.jalumu.magma.platform.bukkit.config;

import de.exlll.configlib.Configuration;
import de.jalumu.magma.text.Text;
import lombok.AllArgsConstructor;

@Configuration
public class TestConfig {
    private Text test1;

    private Text test2;

    public TestConfig(Text test1, Text test2) {
        this.test1 = test1;
        this.test2 = test2;
    }


}
