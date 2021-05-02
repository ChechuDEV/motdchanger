package dev.tsetsi.motdchanger.bukkit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.Collections;
import java.util.List;

public class Commands implements CommandExecutor, TabExecutor {
    MOTD motd;
    public Commands(MOTD motd) {
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
                        motd.setRotationEnabled(!motd.isRotationEnabled());
                        return true;
                    case "enable":
                        motd.setRotationEnabled(true);
                        return true;
                    case "disable":
                        motd.setRotationEnabled(false);
                        return true;
                    case "set":
                        sender.sendMessage(ChatColor.RED + "[MOTDChanger] I am still working on this command so it doesn't work.");
                        // TODO: 27/02/2021 SET
                        return true;
                    case "remove":
                        sender.sendMessage(ChatColor.RED + "[MOTDChanger] I am still working on this command so it doesn't work.");
                        // TODO: 27/02/2021 REMOVE
                        return true;
                    default:
                        help(sender,"rotation");
                        return true;
                }
            default:
                if (!args[0].equals("help"))
                    sender.sendMessage(ChatColor.RED + "[MOTDChanger] The requested command does not exist.");
                help(sender, "all");
                return true;
        }
    }

    private void help(CommandSender sender, String request) {
        sender.sendMessage(ChatColor.GREEN + String.format("[MOTDChanger] Helping with %s commands", request));
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
