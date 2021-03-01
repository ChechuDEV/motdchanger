package dev.tsetsi.motdchanger.bukkit;

import me.lucko.commodore.Commodore;
import me.lucko.commodore.CommodoreProvider;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    private Logger logger;

    @Override
    public void onEnable() {
        logger = getLogger();
        logger.info("Remember to rate and share this plugin. You can also join my discord server: discord.darkdragon.dev");

        // CONFIGURATION FILE CREATION
        saveDefaultConfig();

        // CONFIGURATION MIGRATION
        boolean migrated = false;
        if (!getConfig().contains("metrics")) getConfig().set("metrics",true);
        if (!getConfig().contains("check-updates")) if(getConfig().contains("checkupdates")) {
            getConfig().set("check-updates",getConfig().get("checkupdates"));
            getConfig().set("checkupdates",null);
            migrated = true;
        } else {
            getConfig().set("check-updates",true);
            migrated = true;
        }
        if (!getConfig().contains("rotation")) getConfig().set("rotation",false);
        if (getConfig().contains("permanent-motd") && !(getConfig().get("permanent-motd") instanceof List)) {
            String[] lines = getConfig().getString("permanent-motd").split("%newline%",2);
            List<String> permanentMotd = new ArrayList<>(Arrays.asList(lines));
            if (!getConfig().getString("permanent-motd").contains("%newline%")) permanentMotd.add("");
            getConfig().set("permanent-motd", permanentMotd);
            migrated = true;
        } else if (!getConfig().contains("permanent-motd")) {
            List<String> permanentMotd = new ArrayList<>(Arrays.asList("§bServer is running smooth...", "&6Be happy!"));
            getConfig().set("permanent-motd", permanentMotd);
            migrated = true;
        }
        if (!getConfig().contains("rotating-motds")) {
            List<String> rotatingMotd = new ArrayList<>(Arrays.asList("§bServer is rotating smooth...","&6Rotate happily!"));
            List<List<String>> rotatingMotds = new ArrayList<>(Collections.singletonList(rotatingMotd));
            getConfig().set("rotating-motds",rotatingMotds);
            migrated = true;
        }

        File oldMotdFile = new File(getDataFolder(), "motds.yml");
        if (oldMotdFile.exists()) {
            logger.info("Your configuration file will be updated with your configuration in motds.yml.");
            FileConfiguration motdsFile = YamlConfiguration.loadConfiguration(oldMotdFile);
            if (motdsFile.contains("permanent-motd")) {
                String[] lines = motdsFile.getString("permanent-motd").split("%newline%",2);
                List<String> permanentMotd = new ArrayList<>(Arrays.asList(lines));
                if (!motdsFile.getString("permanent-motd").contains("%newline%")) permanentMotd.add("");
                getConfig().set("permanent-motd", permanentMotd);
                migrated = true;
            }
            List<List<String>> rotatingMotds = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                if (motdsFile.contains("motd-rotation"+i) && !motdsFile.getString("motd-rotation" + i).equals("")){
                    String[] lines = motdsFile.getString("motd-rotation"+i).split("%newline%",2);
                    List<String> rotatingMotd = new ArrayList<>(Arrays.asList(lines));
                    if (!motdsFile.getString("motd-rotation"+i).contains("%newline%")) rotatingMotd.add("");
                    rotatingMotds.add(rotatingMotd);
                }
                if (i == 10) getConfig().set("rotating-motds", rotatingMotds);
                migrated = true;
            }
            File endMotdFile = new File(getDataFolder(), "oldmotds.yml");
            if(oldMotdFile.renameTo(endMotdFile)) logger.info("The motds.yml file has been renamed to oldmotds.yml.");
        }
        if(migrated) {
            saveConfig();
            logger.info("Your configuration has migrated to a new version, please check that everything is okay. Comments may be deprecated...");
        }

        // COMMAND CREATION
        PluginCommand command = getCommand("motdchange");

        command.setExecutor(new Commands());

        if (CommodoreProvider.isSupported()) {
            try {
                Commodore commodore = CommodoreProvider.getCommodore(this);
                Brigadier.register(this,commodore,command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}