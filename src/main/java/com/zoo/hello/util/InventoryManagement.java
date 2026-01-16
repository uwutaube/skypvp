package com.zoo.hello.util;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.util.EventTitleUtil;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.List;

public class InventoryManagement {
    public enum ArmorType {
        LOW,
        MEDIUM,
        HIGH,
    }

    // time is the start time not the expiration
    static Map<UUID, Instant> rifleCooldown = new HashMap<>();

    public static void giveStarterKit(Player player) {
        Inventory inventory = player.getInventory();
        inventory.clear();

        ItemContainer container = inventory.getCombinedHotbarFirst();
        ItemStack[] items = {
                new ItemStack("Weapon_Daggers_Fang_Doomed", 1),
                new ItemStack("Tool_Pickaxe_Cobalt", 1),
        };

        ItemContainer armor = inventory.getCombinedArmorHotbarStorage();

        armor.addItemStack(new ItemStack("Armor_Kweebec_Head", 1));
        armor.addItemStack(new ItemStack("Armor_Leather_Light_Chest", 1));

        for (ItemStack item : items) {
            container.addItemStack(item);
        }
    }

    public static void giveRifleCrateLoot(Player player, PlayerRef playerRef) {
        if (!checkCooldown(player, playerRef, rifleCooldown, 30)) {
            return;
        }

        rifleCooldown.put(playerRef.getUuid(), Instant.now().plusSeconds(30));

        Inventory inventory = player.getInventory();
        ItemContainer container = inventory.getCombinedHotbarFirst();

        LinkedList<ItemStack> items = new LinkedList<>(List.of(new ItemStack("Weapon_Arrow_Crude", 100)));

        int rifleCount = inventory.getCombinedEverything().countItemStacks(
                itemStack -> itemStack.getItemId().equals("Weapon_Assault_Rifle")
        );

        if (rifleCount == 0) {
            items.addFirst(new ItemStack("Weapon_Assault_Rifle", 1));
        } else {
            player.sendMessage(Message.raw("You already have " + rifleCount + " Rifles"));
        }


        for (ItemStack item : items) {
            container.addItemStack(item);
        }
    }

    public static void giveArmorLoot(Player player, PlayerRef playerRef, ArmorType armorType) {
        Inventory inventory = player.getInventory();
        ItemContainer container = inventory.getCombinedArmorHotbarStorage();

        inventory.getArmor().clear();
        container.addItemStacks(getItemStacksByArmorType(armorType));

        EventTitleUtil.showEventTitleToPlayer(playerRef, Message.raw(armorTypeToString(armorType)).color(Color.magenta), Message.raw("Armor looted!"), true, null, 4.0f, 0.5f, 0.5f);
    }

    private static String armorTypeToString(ArmorType armorType) {
        return switch (armorType) {
            case LOW -> "Bronze";
            case MEDIUM -> "Cobalt";
            case HIGH -> "ONYXIUM!";
        };
    }

    private static List<ItemStack> getItemStacksByArmorType(ArmorType armorType) {
        List<ItemStack> armor = switch (armorType) {
            case LOW -> List.of(
                    new ItemStack("Armor_Bronze_Head", 1),
                    new ItemStack("Armor_Bronze_Chest", 1),
                    new ItemStack("Armor_Bronze_Hands", 1),
                    new ItemStack("Armor_Bronze_Legs", 1)
            );
            case MEDIUM -> List.of(
                    new ItemStack("Armor_Cobalt_Head", 1),
                    new ItemStack("Armor_Cobalt_Chest", 1),
                    new ItemStack("Armor_Cobalt_Hands", 1),
                    new ItemStack("Armor_Cobalt_Legs", 1)
            );
            case HIGH -> List.of(
                    new ItemStack("Armor_Onyxium_Head", 1),
                    new ItemStack("Armor_Onyxium_Chest", 1),
                    new ItemStack("Armor_Onyxium_Hands", 1),
                    new ItemStack("Armor_Onyxium_Legs", 1)
            );
        };

        return armor;
    }

    private static boolean checkCooldown(Player player, PlayerRef playerRef, Map<UUID, Instant> cooldownMap, int cooldownSeconds) {
        Instant cooldown = Instant.now().minusSeconds(cooldownSeconds);
        cooldownMap.entrySet().removeIf(entry -> entry.getValue().isBefore(cooldown));
        Instant playerCooldown = cooldownMap.get(playerRef.getUuid());

        if (playerCooldown != null) {
            long secondsLeft = Duration.between(Instant.now(), playerCooldown.plusSeconds(cooldownSeconds)).toSeconds();

            player.sendMessage(Message.raw("You have to wait " + secondsLeft + " seconds before doing that again."));
            return false;
        }

        return true;
    }
}

