package dev.darkdragon.motdchanger.commands;

import dev.darkdragon.motdchanger.motdchanger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class motdchange implements CommandExecutor, TabCompleter {
    motdchanger plugin;
    public motdchange(motdchanger pl) {
        plugin = pl;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Checks if sender has permission
        if(!commandSender.hasPermission("motdchanger")) {
            noPerms(commandSender);
            return false;
        }

        // Shows allowed command list
        if (strings.length == 0){
            helpShow(commandSender);
            return true;
        }

        // Join only MOTD words without subcommand
        ArrayList<String> motdString = new ArrayList<>();
        if(strings.length >= 2) {
            motdString.addAll(Arrays.asList(strings).subList(1, strings.length));
        }else{
            motdString.add("");
        }

        StringBuilder sb = new StringBuilder();

        for (String string : motdString) {
            sb.append(string);
            sb.append(" ");
        }

        // Command calls
        String commandString = strings[0];

        switch(commandString) {
            // Help command
            case "help":
                helpShow(commandSender);
                return true;
            // Info command
            case "info":
                infoShow(commandSender);
                return true;
            // Temporary command
            case "temporary":
                if(commandSender.hasPermission("motdchanger.temporary")){
                    changeMotd(false, sb.toString());
                    commandSender.sendMessage(ChatColor.AQUA + "[MotdChanger] " + ChatColor.GREEN + "MOTD changed temporarily");
                    return true;
                } else {
                    noPerms(commandSender);
                    return false;
                }

            // Permanent command
            case "permanent":
                if(commandSender.hasPermission("motdchanger.permanent")){
                    changeMotd(true, sb.toString());
                    commandSender.sendMessage(ChatColor.AQUA + "[MotdChanger] " + ChatColor.GREEN + "MOTD changed permanent");
                    return true;
                } else {
                    noPerms(commandSender);
                    return false;
                }
            // Reload command
            case "reload":
                if(commandSender.hasPermission("motdchanger.reload")) {
                    reload();
                    commandSender.sendMessage(ChatColor.AQUA + "[MotdChanger] " + ChatColor.GREEN + "Reloaded successfully");
                    return true;
                } else {
                    noPerms(commandSender);
                    return false;
                }
            default:
                commandSender.sendMessage(ChatColor.RED + "[MotdChanger] Command not found");
        }


        return false;
    }

    void helpShow(CommandSender sender) {

        // Initializes string and adds the two default commands
        String allowedCommands =
                ChatColor.GOLD + "/motdchange help\n" + ChatColor.AQUA + "- This. Shows all the allowed commands for you.\n" +
                ChatColor.GOLD + "/motdchange info\n" + ChatColor.AQUA + "- Shows the information of this plugin.\n";

        // Checks if sender has motdchanger.temporary permission to add it to the text
        if(sender.hasPermission("motdchanger.temporary")){
            allowedCommands += ChatColor.GOLD + "/motdchange temporary <motd>\n" + ChatColor.AQUA + "- Allows you to change the server MOTD temporarily. Meaning that if you reload the plugin or restart/reload the server it will be lost and will change to the permanent.\n";
        }

        // Checks if sender has motdchanger.permanent permission to add it to the text
        if(sender.hasPermission("motdchanger.permanent")){
            allowedCommands += ChatColor.GOLD + "/motdchange permanent <motd>\n" + ChatColor.AQUA + "- Allows you to change the server MOTD permanently. Meaning that even if you restart the server, this motd will apear until you change it from the config.yml file or use this command another time to change it\n";
        }

        // Checks if sender has motdchanger.reload permission to add it to the text
        if(sender.hasPermission("motdchanger.reload")) {
            allowedCommands += ChatColor.GOLD + "/motdchange reload\n" + ChatColor.AQUA + "- Allows you to reload the plugin. Reloading will make you loose the temporary motd and the permanent will be set instead.";
        }


        sender.sendMessage(
                ChatColor.AQUA + "[MotdChanger] " + ChatColor.GREEN + " Hello, " + ChatColor.ITALIC + "" + ChatColor.RED + sender.getName() + ChatColor.RESET + "" + ChatColor.GREEN + ", the commands available for you are:\n"+allowedCommands
        );
    }

    void infoShow(CommandSender sender) {
        sender.sendMessage(
                ChatColor.AQUA + "[MotdChanger] " + ChatColor.GREEN + " This plugin is made by DarkDragon\nVisit him in his spigot page to see other projects: "
                + ChatColor.YELLOW + "spigotmc.org/members/598499/ \n"+ ChatColor.GREEN + "This plugin is in the version 2.0. You can check more details in the plugin's page: "
                + ChatColor.YELLOW + "spigotmc.org/resources/63607/ \n"+ ChatColor.GREEN + "If you really like this plugin you can help it's author by donating and/or giving your review about the plugin in spigot"
        );
    }

    void changeMotd(Boolean permanent, String motd){
        plugin.Motd = motd;

        if (permanent) {
            plugin.getConfig().set("permanent-motd",motd);
        }
    }

    void reload() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if(!configFile.exists()){
            plugin.sendMessage("Config file doesn't exists, creating one");
            plugin.saveResource("config.yml", false);
        }

        plugin.Motd = plugin.getConfig().getString("permanent-motd");
    }
    void noPerms(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "[MotdChanger] You do not have permissions for this command!");
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        ArrayList<String> completion = new ArrayList<>();
        if(commandSender.hasPermission("motdchanger")){
            if (strings.length == 1){
                completion.add("help");
                completion.add("info");
                if(commandSender.hasPermission("motdchanger.temporary")) completion.add("temporary");
                if(commandSender.hasPermission("motdchanger.permanent")) completion.add("permanent");
                if(commandSender.hasPermission("motdchanger.reload")) completion.add("reload");
                return completion;
            }
            return completion;
        }
        return completion;

    }
}
