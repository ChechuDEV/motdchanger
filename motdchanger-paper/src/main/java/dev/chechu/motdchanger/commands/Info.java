package dev.chechu.motdchanger.commands;

import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class Info extends Command {
    public Info() {
        super("Displays information about this plugin", Collections.emptyList(), "info", Collections.emptyList());
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }
}
