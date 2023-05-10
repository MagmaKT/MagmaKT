package de.jalumu.magma.cloud;

public class CloudProvider {

    private static Cloud cloud;

    protected static boolean isAvailable = false;

    protected static Cloud getCloud() {
        return cloud;
    }

    public static void setCloud(Cloud cloud) {
        CloudProvider.cloud = cloud;
        CloudProvider.isAvailable = true;
    }


}
