package com.zoo.hello;

import com.hypixel.hytale.component.Holder;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.asset.type.model.config.Model;
import com.hypixel.hytale.server.core.asset.type.model.config.ModelAsset;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;

import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.EntityModule;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.awt.*;
import java.util.UUID;

public class SpawnerCommand extends CommandBase {
    public SpawnerCommand() {
        super("spawner", "Create a spawner");
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext ctx) {
        Player player = (Player) ctx.sender();
        UUID playerUUID = ctx.sender().getUuid();
        PlayerRef playerRef = Universe.get().getPlayer(playerUUID);

        player.sendMessage(Message.raw("You are a player").color(Color.blue));

        World world = player.getWorld();
        if (world == null) {
            return;
        }
        Store<EntityStore> store = world.getEntityStore().getStore();

        world.execute(() -> {
            Holder<EntityStore> holder = EntityStore.REGISTRY.newHolder();
            ModelAsset modelAsset = ModelAsset.getAssetMap().getAsset("Minecart");
            if (modelAsset == null) {
                return;
            }

            Model model = Model.createScaledModel(modelAsset, 1.0f);

            TransformComponent transformComponent = store.getComponent(playerRef.getReference(), EntityModule.get().getTransformComponentType());

        });
    }
}
