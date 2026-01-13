package com.jamo.hello;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

public final class HelloPlugin extends JavaPlugin {
    public HelloPlugin(JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        getLogger().at(java.util.logging.Level.INFO).log("HelloPlugin setup()");

        getCommandRegistry().registerCommand(new HelloCommand());

        getEntityStoreRegistry().registerSystem(new HelloBreakBlockSystem());
    }

    @Override
    protected void start() {
        getLogger().at(java.util.logging.Level.INFO).log("HelloPlugin start()");
    }

    @Override
    protected void shutdown() {
        getLogger().at(java.util.logging.Level.INFO).log("HelloPlugin shutdown()");
    }
}

