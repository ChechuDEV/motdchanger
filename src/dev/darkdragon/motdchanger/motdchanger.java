package dev.darkdragon.motdchanger;

import dev.darkdragon.motdchanger.commands.motdchange;
import dev.darkdragon.motdchanger.events.motdchangeevent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class motdchanger extends JavaPlugin {
    public String Motd = "";
    @Override
    public void onEnable() {
        // Ad text
        sendMessage("This plugin has been made by me, DarkDragon. I would be very grateful if you share and rate the plugin in its spigot page: " + ChatColor.GREEN + " spigot.org/resources/63607/");

        // Main command initialization
        PluginCommand motdchange = this.getCommand("motdchange");
        assert motdchange != null;
        motdchange.setExecutor(new motdchange(this));

        // MotdChange Event initialization
        getServer().getPluginManager().registerEvents(new motdchangeevent(this),this);

        // Checks if config file exists
        File configFile = new File(getDataFolder(), "config.yml");
        if(!configFile.exists()){
            sendMessage("Config file doesn't exists, creating one");
            saveResource("config.yml", false);
        }

        // Sets motd to the config file MOTD
        Motd = getConfig().getString("permanent-motd");

        // Checks if server wants to be tracked with bstats metrics
        if(getConfig().getBoolean("metrics",true)) {
            sendMessage("Enabling BStats Metrics");
            Metrics metrics = new Metrics(this,	4679);
            if(metrics.isEnabled()){
                sendMessage("BStats Metrics enabled");
            }
        }

        // TODO: Missing autoupdate

        super.onEnable();
    }
    @Override
    public void onDisable() {
        // Ad text
        sendMessage("Remember to share and rate the plugin in its spigot page: " + ChatColor.GREEN + " spigotmc.org/resources/63607 ");

        super.onDisable();
    }

    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[MotdChanger]" + ChatColor.YELLOW + message);
    }
}
