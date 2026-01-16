package com.zoo.hello;

import com.hypixel.hytale.server.core.event.events.player.AddPlayerToWorldEvent;
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
        getCommandRegistry().registerCommand(new KitCommand());
        getCommandRegistry().registerCommand(new HologramCommand());
        getCommandRegistry().registerCommand(new SpawnerCommand());
        getCommandRegistry().registerCommand(new KillMessageCommand());

        getEntityStoreRegistry().registerSystem(new BreakBlockSystem());
        getEntityStoreRegistry().registerSystem(new PlayerSpawnSystem());
        getEntityStoreRegistry().registerSystem(new DamageSystem());
        getEntityStoreRegistry().registerSystem(new PlaceBlockSystem());

        getEventRegistry().registerGlobal(AddPlayerToWorldEvent.class, PlayerReadyListener::onAddPlayerToWorld);

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

