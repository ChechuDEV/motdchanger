package dev.tsetsi.motdchanger.bukkit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class Commands implements CommandExecutor, TabExecutor {
    Motd motd;
    public Commands(Motd motd) {
        this.motd = motd;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // CHECK FOR SENDER'S PERMISSIONS
        if (!sender.hasPermission("motdchanger")) {
            sender.sendMessage(ChatColor.RED + "[MOTDChanger] You do not have enough permissions to use this command.");
            return true;
        }
        if (args.length == 0) {
            help(sender);
            return true;
        }
        switch (args[0]) {
            case "info":
                // TODO LOCALE
                sender.sendMessage(ChatColor.GREEN + "[MOTDChanger] " + ChatColor.YELLOW + "This plugin allows you to change your server's " +
                        "MOTD easily and on the go! Get it on " + ChatColor.ITALIC + "" + ChatColor.BLUE + "spigotmc.org/resources/63607/");
                return true;
            case "temporary":
                changeMotd(sender, false, args);
                return true;
            case "permanent":
                changeMotd(sender, true, args);
                return true;
            case "reload":
                reload(sender);
                return true;
            case "rotation":
                switch (args[1]) {
                    case "toggle":
                        // TODO TOGGLE
                        return true;
                    case "enable":
                        // TODO TOGGLE TO ENABLE
                        return true;
                    case "disable":
                        // TODO: 27/02/2021
                        return true;
                    case "set":
                        // TODO: 27/02/2021
                        return true;
                    case "remove":
                        // TODO: 27/02/2021
                        return true;
                    default:
                        // TODO: 27/02/2021
                        return true;
                }
            default:
                if (!args[0].equals("help"))
                    sender.sendMessage(ChatColor.RED + "[MOTDChanger] The requested command does not exist.");
                help(sender);
                return true;
        }
    }

    private void help(CommandSender sender) {

    }

    private void reload(CommandSender sender) {

    }

    private void changeMotd(CommandSender sender, boolean permanent, String[] args) {
        // TODO FADING COLOUR
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }
}
