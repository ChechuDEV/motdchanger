package dev.tsetsi.motdchanger.bukkit;

import me.lucko.commodore.Commodore;
import me.lucko.commodore.CommodoreProvider;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;
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
        boolean migrated;
        migrated = permanentMOTDCorrector();
        migrated = metricsCorrector() || migrated;
        migrated = updateCorrector() || migrated;
        migrated = rotationCorrector() || migrated;
        migrated = rotatingMOTDSCorrector() || migrated;
        migrated = migrateOldMotd() || migrated;

        if(migrated) {
            saveConfig();
            logger.info("Your configuration has migrated to a new version, please check that everything is okay. Comments may be deprecated...");
        }

        // COMMAND CREATION
        MOTD motd = new MOTD(this);
        PluginCommand command = getCommand("motdchange");
        assert command != null;
        command.setExecutor(new Commands(motd));

        // COMMODORE
        if (CommodoreProvider.isSupported()) {
            try {
                Commodore commodore = CommodoreProvider.getCommodore(this);
                Brigadier.register(this,commodore,command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // EVENT REGISTER
        getServer().getPluginManager().registerEvents(new PingEvent(motd),this);

    }

    private boolean permanentMOTDCorrector() {
        if (!getConfig().contains("permanent-motd") || !(getConfig().get("permanent-motd") instanceof String)) {
            getConfig().set("permanent-motd","§bServer is running smooth...%newline%§6Be happy!");
            return true;
        }
        return false;
    }

    private boolean metricsCorrector() {
        if (!getConfig().contains("metrics") || !(getConfig().get("metrics") instanceof Boolean)) {
            getConfig().set("metrics", true);
            return true;
        }
        return false;
    }

    private boolean updateCorrector() {
        if (getConfig().contains("checkupdates") && getConfig().get("checkupdates") instanceof Boolean) {
            getConfig().set("check-updates", getConfig().get("checkupdates"));
            getConfig().set("checkupdates",null);
            return true;
        }
        if (!getConfig().contains("check-updates") || !(getConfig().get("check-updates") instanceof Boolean)) {
            getConfig().set("check-updates", true);
            return true;
        }
        return false;
    }

    private boolean rotationCorrector() {
        if (!getConfig().contains("rotation") || !(getConfig().get("rotation") instanceof Boolean)) {
            getConfig().set("rotation", false);
            return true;
        }
        return false;
    }

    private boolean rotatingMOTDSCorrector() {
        if (!getConfig().contains("rotating-motds") || !(getConfig().get("rotating-motds") instanceof List)) {
            List<String> rotatingMotds = Collections.singletonList("§bServer is rotating smooth...%newline%§6Rotate happily!");
            getConfig().set("rotating-motds",rotatingMotds);
            return true;
        }
        return false;
    }

    private boolean migrateOldMotd() {
        File oldMotdFile = new File(getDataFolder(),"motds.yml");
        if(oldMotdFile.exists()) {
            logger.info("Your configuration file will be updated with your old configuration in motds.yml");
            FileConfiguration motdsFile = YamlConfiguration.loadConfiguration(oldMotdFile);
            if (motdsFile.contains("permanent-motd") && motdsFile.get("permanent-motd") != "" && motdsFile.get("permanent-motd") instanceof String) {
                String permanentMotd = Objects.requireNonNull(motdsFile.getString("permanent-motd"));
                getConfig().set("permanent-motd", permanentMotd);
            }
            List<String> rotatingMotds = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                if (motdsFile.contains("motd-rotation"+i) && !Objects.equals(motdsFile.getString("motd-rotation" + i), "") && motdsFile.get("motd-rotation" + i) instanceof String){
                    String rotatingMotd = Objects.requireNonNull(motdsFile.getString("motd-rotation" + i));
                    rotatingMotds.add(rotatingMotd);
                }
                if (i == 10) getConfig().set("rotating-motds", rotatingMotds);
            }
            File endMotdFile = new File(getDataFolder(), "oldmotds.yml");
            if(oldMotdFile.renameTo(endMotdFile)) logger.info("The motds.yml file has been renamed to oldmotds.yml.");
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}