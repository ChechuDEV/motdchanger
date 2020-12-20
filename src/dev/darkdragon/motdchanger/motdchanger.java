package dev.darkdragon.motdchanger;

import dev.darkdragon.motdchanger.commands.motdchange;
import dev.darkdragon.motdchanger.events.motdchangeevent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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
        } else {
            if(!getConfig().contains("metrics")) getConfig().set("metrics", true);
            if(!getConfig().contains("checkupdates")) getConfig().set("checkupdates", true);
            if(!getConfig().contains("autoupdate")) getConfig().set("autoupdate", true);
            if(!getConfig().contains("rotation")) getConfig().set("rotation", true);
            saveConfig();
        }
        File motds = new File(getDataFolder(), "motds.yml");
        FileConfiguration motdsFile = YamlConfiguration.loadConfiguration(motds);
        if(!motds.exists()) {
            sendMessage("MOTDs file doesn't exists, creating one");
            saveResource("motds.yml", false);
            Motd = "§bServer is running smooth...%newline%&6Be happy!";
        } else {
            if(!motdsFile.contains("permanent-motd")) {
                motdsFile.set("permanent-motd","§bServer is running smooth...%newline%&6Be happy!");
                Motd = "§bServer is running smooth...%newline%&6Be happy!";
            } else {
                Motd = motdsFile.getString("permanent-motd");
            }
            for ( int i = 1; i <= 10; i++) {
                if(!motdsFile.contains("motd-rotation"+i)) motdsFile.set("motd-rotation"+i, "");
            }
            try {
                motdsFile.save(motds);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        // Checks if server wants to be tracked with bstats metrics
        if(getConfig().getBoolean("metrics",true)) {
            sendMessage("Enabling BStats Metrics");
            Metrics metrics = new Metrics(this,	4679);
            if(metrics.isEnabled()){
                sendMessage("BStats Metrics enabled");
            }
        }

        // TODO: Missing autoupdate
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                update();
            }
        };
        timer.schedule(task, 0, TimeUnit.MINUTES.toMillis(30));

        super.onEnable();
    }

    public void update() {
        new Updater(this);
    }

    @Override
    public void onDisable() {
        // Ad text
        sendMessage("Remember to share and rate the plugin in its spigot page: " + ChatColor.GREEN + " spigotmc.org/resources/63607 ");

        super.onDisable();
    }

    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[MotdChanger] " + ChatColor.YELLOW + message);
    }
}
