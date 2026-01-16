package com.zoo.hello;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.zoo.hello.util.InventoryManagement;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.util.Objects;

public final class BreakBlockSystem extends EntityEventSystem<EntityStore, BreakBlockEvent> {
    private final String crateId = "Furniture_Village_Crate";

    private final String rock_Gem_Topaz = "Rock_Gem_Topaz";
    private final String rock_Gem_Sapphire = "Rock_Gem_Sapphire";
    private final String rock_Gem_Voidstone = "Rock_Gem_Voidstone";

    public BreakBlockSystem() {
        super(BreakBlockEvent.class);
    }

    @Override
    public void handle(int i, @NonNullDecl ArchetypeChunk<EntityStore> chunk, @NonNullDecl Store<EntityStore> store, @NonNullDecl CommandBuffer<EntityStore> commandBuffer, @NonNullDecl BreakBlockEvent breakBlockEvent) {
        Player player = (Player) chunk.getComponent(i, Player.getComponentType());
        PlayerRef playerRef = (PlayerRef) chunk.getComponent(i, PlayerRef.getComponentType());


        if (player != null && playerRef != null && player.getGameMode() == GameMode.Adventure) {
            if (Objects.equals(breakBlockEvent.getBlockType().getId(), crateId)) {
                InventoryManagement.giveRifleCrateLoot(player, playerRef);
            }

            switch (breakBlockEvent.getBlockType().getId()) {
                case rock_Gem_Topaz:
                    InventoryManagement.giveArmorLoot(player, playerRef, InventoryManagement.ArmorType.LOW);
                    break;
                case rock_Gem_Sapphire:
                    InventoryManagement.giveArmorLoot(player, playerRef, InventoryManagement.ArmorType.MEDIUM);
                    break;
                case rock_Gem_Voidstone:
                    InventoryManagement.giveArmorLoot(player, playerRef, InventoryManagement.ArmorType.HIGH);
                    break;
            }


            breakBlockEvent.setCancelled(true);
        }
    }

    @Override
    public Query<EntityStore> getQuery() {
        return Query.any();
    }
}

