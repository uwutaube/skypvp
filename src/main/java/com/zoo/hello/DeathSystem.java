package com.zoo.hello;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathSystems;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.EventTitleUtil;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class DeathSystem extends DeathSystems.OnDeathSystem {

    @Override
    public void onComponentAdded(@NonNullDecl Ref<EntityStore> ref, @NonNullDecl DeathComponent deathComponent, @NonNullDecl Store<EntityStore> store, @NonNullDecl CommandBuffer<EntityStore> commandBuffer) {
        Damage deathInfo = deathComponent.getDeathInfo();

        if (deathInfo != null && deathInfo.getSource() instanceof Damage.EntitySource source) {
            Ref<EntityStore> killerRef = source.getRef();
            if (killerRef.isValid()) {
                PlayerRef killerPlayerRef = store.getComponent(killerRef, PlayerRef.getComponentType());
                if (killerPlayerRef != null) {
                    EventTitleUtil.showEventTitleToPlayer(killerPlayerRef, Message.raw("+4 Coins"), Message.raw("KILL!"), true, null, 4.0f, 0.5f, 0.5f);

                    Player killerPlayer = store.getComponent(killerRef, Player.getComponentType());
                    if (killerPlayer != null) {
                        Inventory killerPlayerInventory = killerPlayer.getInventory();
                        ItemContainer container = killerPlayerInventory.getCombinedHotbarFirst();

                        container.addItemStack(new ItemStack("Potion_Health_Large", 1));
                    }
                }
            }
        }
    }

    @NullableDecl
    @Override
    public Query<EntityStore> getQuery() {
        return Player.getComponentType();
    }
}
