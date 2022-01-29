package dev.chechu.motdchanger.commands;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class Command {
    /*
    * /motdchanger  motd - Displays motd help
    *               COMMAND
    * /motdchanger  motd    temporary   <motd> - Changes...
    *               COMMAND COMMAND     PARAM
    *
    * TODO: Help iterator!!!
    *
    * */
    String description;
    List<String> params;
    String command;
    List<String> subcommands;

    public String getCommand() {
        return command;
    }

    public List<String> getSubcommands() {
        return subcommands;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getParams() {
        return params;
    }

    public Command(String description, List<String> params, String command, List<Sub> subcommands) {
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
}
