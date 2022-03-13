package dev.chechu.motdchanger.paper.commands;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.commands.CommandManager;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Message;
import dev.chechu.dragonapi.core.utils.Sender;

public class Info implements Command {
    @Override
    public void execute(Sender<?> sender, String[] strings, CommandManager<?> manager) {
        sender.sendMessage(Message.get("info"));
    }

    @Override
    public Description getDescription() {
        return null;
    }
}
