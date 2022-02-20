package dev.chechu.motdchanger.paper.commands;

import dev.chechu.motdchanger.common.commands.Command;
import dev.chechu.motdchanger.common.commands.CommandManager;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class Help extends Command {
    CommandManager manager;
    public Help(CommandManager manager) {
        super("Displays this help section", List.of("%ocommand"), "help", Collections.emptyList());
        this.manager = manager;
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(manager.getAllHelp());
    }
}
