package dev.chechu.motdchanger.commands;

import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class Help extends Command {
    CommandManager manager;
    public Help(CommandManager manager) {
        super("Displays this help section", List.of("%command"), "help", Collections.emptyList());
        this.manager = manager;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(manager.getAllHelp());
    }
}
