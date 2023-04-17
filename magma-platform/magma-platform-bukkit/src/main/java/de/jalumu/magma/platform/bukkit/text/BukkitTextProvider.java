package de.jalumu.magma.platform.bukkit.text;

import de.jalumu.magma.text.Text;
import de.jalumu.magma.text.TextProvider;

public class BukkitTextProvider extends TextProvider {
    @Override
    protected Text text(String text) {
        return new BukkitText().setText(text);
    }
}
