package com.zoo.hello;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;

public final class HelloCommand extends CommandBase {
    public HelloCommand() {
        super("hello", "Says hello");
    }

    @Override
    protected void executeSync(CommandContext ctx) {
        String myName = "hello :D 2";

        ctx.sendMessage(Message.raw(myName));
    }
}

