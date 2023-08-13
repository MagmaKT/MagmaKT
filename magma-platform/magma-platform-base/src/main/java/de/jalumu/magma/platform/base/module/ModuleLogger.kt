package de.jalumu.magma.platform.base.module

import java.util.logging.Logger

class ModuleLogger constructor(moduleName: String, parent: Logger) : Logger(
    "MagmaKT-$moduleName", null
) {
    init {
        setParent(parent)
        level = parent.level
    }
}
