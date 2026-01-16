package com.zoo.hello;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.ecs.PlaceBlockEvent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import javax.annotation.Nonnull;

public class PlaceBlockSystem extends EntityEventSystem<EntityStore, PlaceBlockEvent> {

    public PlaceBlockSystem() {
        super(PlaceBlockEvent.class);
    }

    @Nonnull
    @Override
    public Query<EntityStore> getQuery() {
        return Query.any();
    }

    @Nonnull
    @Override
    public Class<PlaceBlockEvent> getEventType() {
        return PlaceBlockEvent.class;
    }

    @Override
    public void handle(int i, @NonNullDecl ArchetypeChunk<EntityStore> chunk, @NonNullDecl Store<EntityStore> store, @NonNullDecl CommandBuffer<EntityStore> commandBuffer, @NonNullDecl PlaceBlockEvent placeBlockEvent) {
        Player player = (Player) chunk.getComponent(i, Player.getComponentType());
        if (player != null && player.getGameMode() == GameMode.Adventure) {
            placeBlockEvent.setCancelled(true);
        }
    }
}
