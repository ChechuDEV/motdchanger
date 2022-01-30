package dev.chechu.motdchanger.commands;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;

abstract class Command {
    String description;
    List<String> params;
    String command;
    List<Command> subcommands;

    public String getCommand() {
        return command;
    }

    public List<Command> getSubcommands() {
        return subcommands;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getParams() {
        return params;
    }

    public Command(String description, List<String> params, String command, List<Command> subcommands) {
        this.description = description;
        this.params = params;
        this.command = command;
        this.subcommands = subcommands;
    }

    public abstract void execute(CommandSender sender, String[] args);

    public boolean isConsole(CommandSender sender) {
        return sender instanceof ConsoleCommandSender;
    }

    public boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    public boolean isCommandBlock(CommandSender sender) {
        return sender instanceof BlockCommandSender;
    }

    @Deprecated
    public boolean hasSubcommands() {
        return !subcommands.isEmpty();
    }
}
