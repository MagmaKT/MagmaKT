package de.jalumu.magma.text;

public abstract class TextProvider {

    private static TextProvider textProvider = null;

    public static TextProvider getProvider() {
        return textProvider;
    }

    public static void setTextProvider(TextProvider textProvider) {
        TextProvider.textProvider = textProvider;
    }

    protected abstract Text text(String text);

}
