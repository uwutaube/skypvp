package com.zoo.hello;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.AddPlayerToWorldEvent;
import com.zoo.hello.util.InventoryManagement;

public class PlayerReadyListener {
    private static final HytaleLogger LOGGER = HytaleLogger.get("AddPlayerToWorldEvent");

    public static void onAddPlayerToWorld(AddPlayerToWorldEvent event) {
        Player player = event.getHolder().getComponent(Player.getComponentType());

        if (player != null) {
            InventoryManagement.giveStarterKit(player);
        }
    }
}
