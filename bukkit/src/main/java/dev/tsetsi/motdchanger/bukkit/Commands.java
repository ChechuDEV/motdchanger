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
        sender.sendMessage(ChatColor.GREEN + String.format("[MOTDChanger] Helping with %s commands...", request));
        List<String> helps = new ArrayList<>();
        if (request.equals("all")){
            helps.addAll(getAllCommands(sender));
        }
        helps.addAll(getRotationCommands(sender));
        for (String help : helps) {
            sender.sendMessage(help);
        }
    }

    private List<String> getRotationCommands(CommandSender sender) {
        List<String> helps = new ArrayList<>();
        if (sender.hasPermission("motdchanger.rotation")){
            if (sender.hasPermission("motdchanger.rotation.toggle"))
                helps.addAll(Arrays.asList(ChatColor.DARK_AQUA + "/motdchanger rotation toggle " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Allows you to toggle rotation mode.",
                        ChatColor.DARK_AQUA + "/motdchanger rotation enable/disable " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Allows you to enable or disable rotation mode."));
            if (sender.hasPermission("motdchanger.rotation.set"))
                helps.addAll(Arrays.asList(
                    ChatColor.DARK_AQUA + "/motdchanger rotation set <index/new> <motd> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Allows you to set a rotating MOTD.",
                    ChatColor.DARK_AQUA + "/motdchanger rotation remove <index/all>" + ChatColor.WHITE + "- " + ChatColor.AQUA + "Allows you to remove one or all rotating MOTDs",
                    ChatColor.DARK_AQUA + "/motdchanger rotation get <index/all>" + ChatColor.WHITE + "- " + ChatColor.AQUA + "Allows you to get one or all rotating MOTDs"
                ));
            return helps;
        } else
            return Collections.singletonList(ChatColor.RED + "You have no permission to see rotation MOTD commands. Please contact an administrator if this is not intended. (Missing permission: motdchanger.rotation)");
    }
    private List<String> getAllCommands(CommandSender sender) {
        List<String> helps = new ArrayList<>();
        helps.addAll(Arrays.asList(ChatColor.DARK_AQUA + "/motdchanger info " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Allows you to get information about this plugin.",
                ChatColor.DARK_AQUA + "/motdchanger help <all/rotation>" + ChatColor.WHITE + "- " + ChatColor.AQUA + "Allows you to get help with the commands of this plugin."));
        if (sender.hasPermission("motdchanger.temporary"))
            helps.add(ChatColor.DARK_AQUA + "/motdchanger temporary <motd> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Allows you to set a temporary MOTD.");
        if (sender.hasPermission("motdchanger.permanent"))
            helps.add(ChatColor.DARK_AQUA + "/motdchanger permanent <motd> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Allows you to set a permanent MOTD.");
        if (sender.hasPermission("motdchanger.reload"))
            helps.add(ChatColor.DARK_AQUA + "/motdchanger reload " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Allows you to reload the plugin.");
        return helps;
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
