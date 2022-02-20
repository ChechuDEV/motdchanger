package dev.chechu.motdchanger.paper.commands.motd;

import dev.chechu.motdchanger.paper.Configuration;
import dev.chechu.motdchanger.commands.Command;
import dev.chechu.motdchanger.commands.CommandManager;
import org.bukkit.command.CommandSender;

import java.util.Collections;

public class Get extends Command {
    CommandManager manager;
    Configuration config;
    public Get(CommandManager manager) {
        super("Gets server's MotD", Collections.emptyList(), "get", Collections.emptyList());
        this.manager = manager;
        this.config = manager.getConfig();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(config.getMotD());
    }
}
