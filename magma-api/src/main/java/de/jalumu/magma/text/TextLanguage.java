package de.jalumu.magma.text;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TextLanguage {


    ENGLISH("en_us"),
    GERMAN("de_de");

    private String langCode;

}
