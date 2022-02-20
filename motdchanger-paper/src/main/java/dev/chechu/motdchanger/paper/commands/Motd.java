package dev.chechu.motdchanger.paper.commands;

import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class Motd extends Command {
    CommandManager manager;
    public Motd(CommandManager manager) {
        super("Manage server's MotD", Collections.emptyList(), "motd", Collections.emptyList());
        this.manager = manager;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        // Get help from motd section
    }
}