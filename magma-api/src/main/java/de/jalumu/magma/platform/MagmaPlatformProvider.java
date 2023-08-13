package de.jalumu.magma.platform;

import de.jalumu.magma.text.Text;
import de.jalumu.magma.text.TextProvider;

/**
 * A class that helps getting the @{@link MagmaPlatform} from external Plugins or Modules
 */
public class MagmaPlatformProvider {

    private static MagmaPlatform platform = null;

    public static MagmaPlatform getPlatform() {
        return platform;
    }
    public static void setPlatform(MagmaPlatform platform) {
        MagmaPlatformProvider.platform = platform;
    }

}
