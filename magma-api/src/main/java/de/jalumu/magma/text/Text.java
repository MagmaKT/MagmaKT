package de.jalumu.magma.text;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

@Configuration
@AllArgsConstructor
@Getter
public class Text {

    @Comment("Default / Fallback Text")
    private String text;

    @Comment("Mapping of all other Languages")
    private HashMap<String, String> translations;

}
