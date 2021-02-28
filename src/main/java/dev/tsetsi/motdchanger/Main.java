package dev.tsetsi.motdchanger;

import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    private Logger logger;

    @Override
    public void onEnable() {
        logger = getLogger();
        logger.info("Remember to rate and share this plugin. You can also join my discord server: discord.darkdragon.dev");

        // CONFIGURATION FILES CREATION
        File configFile = new File(getDataFolder(), "config.yml");
        if ( !configFile.exists() ) {
            logger.warning("Config file not found, creating it.");
            if ( getDataFolder().mkdir() ) logger.info("Plugin folder created");
            if ( !createConfigFile() ) {
                logger.severe("Couldn't create the config file, disabling the plugin!");
                getPluginLoader().disablePlugin(this);
            }
        }
        // CONFIGURATION MIGRATION
        if (getConfig().contains("permanent-motd") && !(getConfig().get("permanent-motd") instanceof List)) {
            logger.severe("ASDSDASD");
            String lines[] = getConfig().getString("permanent-motd").split("%newline%",2);
            List<String> permanentMotd = new ArrayList<>(Arrays.asList(lines));
            if (!getConfig().getString("permanent-motd").contains("%newline%")) permanentMotd.add("");
                getConfig().set("permanent-motd", permanentMotd);
        } else if (!getConfig().contains("permanent-motd")) {
            List<String> permanentMotd = new ArrayList<>(Arrays.asList("§bServer is running smooth...", "&6Be happy!"));
            getConfig().set("permanent-motd", permanentMotd);
        }
        if (!getConfig().contains("rotating-motds")) {
            List<String> rotatingMotd = new ArrayList<>(Arrays.asList("§bServer is rotating smooth...","&6Rotate happily!"));
            List<List<String>> rotatingMotds = new ArrayList<>(Collections.singletonList(rotatingMotd));
            getConfig().set("rotating-motds",rotatingMotds);
        }

        File oldMotdFile = new File(getDataFolder(), "motds.yml");
        if (oldMotdFile.exists()) {
            FileConfiguration motdsFile = YamlConfiguration.loadConfiguration(oldMotdFile);
            if (motdsFile.contains("permanent-motd")) {
                String lines[] = motdsFile.getString("permanent-motd").split("%newline%",2);
                List<String> permanentMotd = new ArrayList<>(Arrays.asList(lines));
                if (!motdsFile.getString("permanent-motd").contains("%newline%")) permanentMotd.add("");

                getConfig().set("permanent-motd", permanentMotd);
            }
            List<List<String>> rotatingMotds = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                if (motdsFile.contains("motd-rotation"+i) && !motdsFile.getString("motd-rotation" + i).equals("")){
                    String lines[] = motdsFile.getString("motd-rotation"+i).split("%newline%",2);
                    List<String> rotatingMotd = new ArrayList<>(Arrays.asList(lines));
                    if (!motdsFile.getString("motd-rotation"+i).contains("%newline%")) rotatingMotd.add("");
                    rotatingMotds.add(rotatingMotd);
                }
                if (i == 10) getConfig().set("rotating-motds", rotatingMotds);
            }
        }
        saveConfig();

        // COMMAND CREATION
        getCommand("motdchange").setExecutor(new Commands());
    }

    private boolean createConfigFile() {
        try {
            Files.copy(Objects.requireNonNull(getResource("config.yml")), new File(getDataFolder(), "config.yml").toPath());
            return true;
        } catch (IOException exception) {
            logger.severe(exception.getMessage());
        }
        return false;
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
