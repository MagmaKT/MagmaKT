rootProject.name = "MagmaKT"

// MagmaKt Platform
include("magma-platform")

include("magma-platform:magma-platform-base")
findProject(":magma-platform:magma-platform-base")?.name = "magma-platform-base"

include("magma-platform:magma-platform-bukkit")
findProject(":magma-platform:magma-platform-bukkit")?.name = "magma-platform-bukkit"

include("magma-platform:magma-platform-bungee")
findProject(":magma-platform:magma-platform-bungee")?.name = "magma-platform-bungee"

// MagmaKT Annotations
include("magma-annotations")

include("magma-annotations:magma-annotations-bukkit-platform")
findProject(":magma-annotations:magma-annotations-bukkit-platform")?.name = "magma-annotations-bukkit-platform"

include("magma-annotations:magma-annotations-bungee-platform")
findProject(":magma-annotations:magma-annotations-bungee-platform")?.name = "magma-annotations-bungee-platform"

include("magma-modules")
include("magma-modules:magma-chat-module")
findProject(":magma-modules:magma-chat-module")?.name = "magma-chat-module"
include("magma-modules:magma-tablist-module")
findProject(":magma-modules:magma-tablist-module")?.name = "magma-tablist-module"
include("magma-modules:magma-command-module")
findProject(":magma-modules:magma-command-module")?.name = "magma-command-module"
include("magma-api")
include("magma-modules:magma-dev-module")
findProject(":magma-modules:magma-dev-module")?.name = "magma-dev-module"
include("magma-modules:magma-simplecloud-module")
findProject(":magma-modules:magma-simplecloud-module")?.name = "magma-simplecloud-module"

