package de.jalumu.magma.platform.bungee.text;

import de.jalumu.magma.text.Text;
import de.jalumu.magma.text.TextProvider;

public class BungeeTextProvider extends TextProvider {
    @Override
    protected Text text(String text) {
        return new BungeeText().setText(text);
    }
}
