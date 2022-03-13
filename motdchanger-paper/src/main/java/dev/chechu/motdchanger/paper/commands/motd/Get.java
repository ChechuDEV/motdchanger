package dev.chechu.motdchanger.paper.commands.motd;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.commands.CommandManager;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Sender;
import dev.chechu.motdchanger.paper.Configuration;

public class Get implements Command {
    CommandManager manager;
    Configuration config;
    public Get(CommandManager manager) {
        this.manager = manager;
        this.config = (Configuration) manager.getConfig();
    }

    @Override
    public void execute(Sender<?> sender, String[] strings) {
        sender.sendMessage(config.getMotD());
    }

    @Override
    public Description getDescription() {
        return null;
    }
}
