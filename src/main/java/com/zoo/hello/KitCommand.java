package com.zoo.hello;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.zoo.hello.util.InventoryManagement;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class KitCommand extends CommandBase {
    public KitCommand() {
        super("kit", "Get a starter kit!");
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext ctx) {
        if (!ctx.isPlayer()) {
            return;
        }

        Player player = ctx.senderAs(Player.class);
        InventoryManagement.giveStarterKit(player);
    }
}
