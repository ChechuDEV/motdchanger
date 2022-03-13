package dev.chechu.motdchanger.paper.commands.protocol;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.commands.CommandManager;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Sender;

public class Display implements Command {
    @Override
    public void execute(Sender<?> sender, String[] args, CommandManager<?> commandManager) {
        switch (args[1]) {
            case "default" -> sender.sendMessage("Protocol will be displayed as normal.");
            case "never" -> sender.sendMessage("Protocol will always be fine, without depending on client's and server's version.");
            case "always" -> sender.sendMessage("Protocol will always display Incompatible version and so it will show the Version Name.");
            default -> sender.sendMessage("Protocol is shown <x>.");
        }
    }

    @Override
    public Description getDescription() {
        return null;
    }
}
