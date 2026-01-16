package com.zoo.hello;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.SystemGroup;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.modules.entity.damage.DamageEventSystem;
import com.hypixel.hytale.server.core.modules.entity.damage.DamageModule;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class DamageSystem extends DamageEventSystem {

    @Override
    public void handle(int i, @NonNullDecl ArchetypeChunk<EntityStore> archetypeChunk, @NonNullDecl Store<EntityStore> store, @NonNullDecl CommandBuffer<EntityStore> commandBuffer, @NonNullDecl Damage damage) {
        TransformComponent transformComponent = archetypeChunk.getComponent(i, TransformComponent.getComponentType());
        if (transformComponent == null) {
            return;
        }


        Vector3d targetPosition = transformComponent.getPosition();

        if (targetPosition.y > 230) {
            Player player = archetypeChunk.getComponent(i, Player.getComponentType());
            if (player != null) {
                player.sendMessage(Message.raw(String.valueOf(targetPosition)));
            }
            damage.setCancelled(true);
        }
    }

    @Override
    public SystemGroup getGroup() {
        return DamageModule.get().getFilterDamageGroup();  // Before damage applied
    }


    @NullableDecl
    @Override
    public Query<EntityStore> getQuery() {
        return Player.getComponentType();
    }
}
