package dev.tsetsi.motdchanger.bukkit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Arrays;
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
            help(sender, "all");
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
                if (args.length == 1) {
                    help(sender,"rotation");
                    return true;
                }
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
                        sender.sendMessage(ChatColor.RED + "[MOTDChanger] The requested command does not exist. Do \"/motdchanger help rotation\" to get help with MOTDChanger Rotation commands.");
                        return true;
                }
            case "help":
                if (args.length == 1 || args[1].equals("all")) {
                    help(sender, "all");
                } else if (args[1].equals("rotation")) {
                    help(sender, "rotation");
                } else sender.sendMessage(ChatColor.RED + "[MOTDChanger] The requested help does not exist.");
                return true;
            default:
                sender.sendMessage(ChatColor.RED + "[MOTDChanger] The requested command does not exist. Do \"/motdchanger help\" to get help with MOTDChanger commands.");
                return true;
        }
    }

    private void help(CommandSender sender, String request) {
        sender.sendMessage(ChatColor.GREEN + String.format("[MOTDChanger]" + ChatColor.YELLOW + " Helping with %s commands...", request));
        List<CommandData> helps = new ArrayList<>();

        if (request.equals("all")){
            helps.addAll(Arrays.asList(
                    new CommandData("info", false, "Allows you to get information about this plugin."),
                    new CommandData("help",false, "Allows you to get help with the commands of this plugin.")));
            if (sender.hasPermission("motdchanger.temporary"))
                helps.add(new CommandData("temporary", false, "motd", "Allows you to set a temporary MOTD."));
            if (sender.hasPermission("motdchanger.permanent"))
                helps.add(new CommandData("permanent", false, "motd", "Allows you to set a permanent MOTD."));
            if (sender.hasPermission("motdchanger.reload"))
                helps.add(new CommandData("reload", false, "Allows you to reload the plugin."));
        }
        if (sender.hasPermission("motdchanger.rotation")) {
            if (sender.hasPermission("motdchanger.rotation.toggle"))
                helps.addAll(Arrays.asList(
                        new CommandData("toggle", true, "Allows you to toggle rotation mode on and off."),
                        new CommandData("enable/disable", true, "Allows you to enable or disable rotation mode.")
                ));
            if (sender.hasPermission("motdchanger.rotation.set"))
                helps.addAll(Arrays.asList(
                        new CommandData("get", true, "index/all", "motd", "Allows you to get one or all rotating MOTDs."),
                        new CommandData("set", true, "index/new", "motd", "Allows you to set a rotating MOTD."),
                        new CommandData("remove", true, "index/all", "motd", "Allows you to remove one or all rotating MOTDs.")
                ));
        } else {
            if (request.equals("rotation")) {
                sender.sendMessage(ChatColor.RED + "[MOTDChanger] You have no permissions to see rotation commands, if this wasn't intended, contact your administrator. (Missing permission: motdchanger.rotation)");
            }
        }

        for (CommandData commandData : helps) {
            String rotationEnabled = commandData.getRotationCommand() ? ChatColor.AQUA + "rotation " : "";
            String argument1 = commandData.getArgument1().equals("") ? "" : String.format(ChatColor.LIGHT_PURPLE + " <%s>", commandData.getArgument1());
            String argument2 = commandData.getArgument2().equals("") ? "" : String.format(ChatColor.DARK_PURPLE + " <%s>", commandData.getArgument2());
            sender.sendMessage(" " + ChatColor.DARK_AQUA + "/motdchanger " + rotationEnabled + ChatColor.GREEN + commandData.getCommand() + argument1 + argument2 + ChatColor.YELLOW + "\n - " + commandData.getDescription());
        }
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
