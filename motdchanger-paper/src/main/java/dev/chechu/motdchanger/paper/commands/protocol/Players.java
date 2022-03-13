package dev.chechu.motdchanger.paper.commands.protocol;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.commands.CommandManager;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Sender;

public class Players implements Command {
    @Override
    public void execute(Sender<?> sender, String[] args, CommandManager<?> commandManager) {
        switch (args[3]) {
            case "show" -> sender.sendMessage("Players will now be shown.");
            case "hide" -> sender.sendMessage("Players will now be hidden.");
        }
    }

    @Override
    public Description getDescription() {
        return null;
    }
}
