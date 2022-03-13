package dev.chechu.motdchanger.paper.commands.protocol;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.commands.CommandManager;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Sender;

public class Set implements Command {
    @Override
    public void execute(Sender<?> sender, String[] args, CommandManager<?> commandManager) {
        sender.sendMessage("Protocol text is set to <x>");
    }

    @Override
    public Description getDescription() {
        return null;
    }
}
