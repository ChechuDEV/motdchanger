package dev.chechu.motdchanger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.util.FileUtil;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Configuration {
    private final Main plugin;
    private FileConfiguration config;
    private final Logger logger;

    private boolean rotation = false;
    private boolean hidePlayers = false;
    private boolean checkUpdates = true;
    private boolean autoUpdate = true;
    private boolean metrics = true;

    private List<String> motDs = List.of("&bThe server is working smoothly%newline%&aBe happy! ^^");

    private String versionText = "&4Maintenance!";
    private String motD = "&bThe server is working smoothly%newline%&aBe happy! ^^";

    private int blockProtocol = 0;

    private final String exceptedConfigVersion = "0.1";
    private String currentConfigVersion = "0.1";

    public Configuration(@NotNull Main plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.logger = plugin.getLogger();

        setUpFiles();

        reloadConfig();

        if (!doVersionsMatch()) {
            logger.warning("Detected an older version of the configuration, trying to fix it...");
            createBackup("config.yml");
            fixConfig();
        }
    }

    /**
     * Checks if the necessary files exist, and if not, tries to create them
     * @apiNote Redundant check to config.yml existence, Plugin#saveDefaultConfig already does that check
     */
    private void setUpFiles() {
        if (!(exists(plugin.getDataFolder()))) {
            logger.info("Plugin's folder is missing, trying to create one.");
            createFolder();
        }

        if(!(exists(new File(plugin.getDataFolder(), "config.yml")))) {
            logger.info("Plugin's configuration file is missing, trying to create one.");
            createConfig();
        }
    }

    /**
     * Checks whether the specified file exists or not
     * @param file File to be checked
     * @return Whether the file exists or not
     */
    private boolean exists(@NotNull File file) {
        return file.exists();
    }

    /**
    * Creates the plugin's folder
    */
    private void createFolder() {
        if (plugin.getDataFolder().mkdir()) {
            logger.info("Plugin's folder has been created");
        } else {
            logger.severe("Plugin's folder has been unable to be created. Perhaps there are no permissions?");
            logger.severe("Disabling the plugin...");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }

    /**
     * Creates the plugin's config.yml file
     */
    private void createConfig() {
        plugin.saveDefaultConfig();
        logger.info("Default configuration has been created");
    }

    /**
     * Checks if config.yml version matches the excepted config version
     * @return Whether the config.yml version is the latest version or not
     */
    private boolean doVersionsMatch() {
        return exceptedConfigVersion.equals(currentConfigVersion);
    }

    /**
     * Creates a copy of the file but with .backup extension.
     * @param fileName File withing plugin's folder to be backed up
     */
    private void createBackup(String fileName) {
        logger.info("Creating a backup file of " + fileName);
        FileUtil.copy(new File(plugin.getDataFolder(), fileName), new File(plugin.getDataFolder(), fileName+".backup"));
    }

    /**
     * Fixes the configuration
     * @apiNote Used when versions are mismatched<p>
     * Note that version checker isn't implemented within this function
     */
    private void fixConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();

        config.set("rotation", rotation);
        config.set("motds", motDs);
        config.set("block-protocol", convertBlockProtocolIDtoState(blockProtocol));
        config.set("version-text", versionText);
        config.set("hide-players", hidePlayers);
        config.set("check-updates", checkUpdates);
        config.set("auto-update", autoUpdate);
        config.set("metrics", metrics);

        logger.info("Configuration has been fixed. Anyways, a backup file has been created.");
    }

    /**
     * Reloads the configuration
     */
    private void reloadConfig() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();

        rotation = config.getBoolean("rotation");
        hidePlayers = config.getBoolean("hide-players");
        checkUpdates = config.getBoolean("check-updates");
        autoUpdate = config.getBoolean("auto-update");
        metrics = config.getBoolean("metrics");

        motDs = config.getStringList("motds");

        versionText = config.getString("version-text");
        blockProtocol = convertBlockProtocolStateToID(config.getString("block-protocol"));

        currentConfigVersion = config.getString("version");
    }

    /**
     * Converts the block protocol state to an ID
     * @param state State of the block protocol
     * @return Block protocol ID from state
     */
    private int convertBlockProtocolStateToID(@Nullable String state) {
        if (state == null || state.equals("default")) return 0;
        if (state.equals("never")) return 1;
        if (state.equals("yes")) return 2;
        return 0;
    }

    /**
     * Converts the block protocol ID to a state
     * @param id ID of the block protocol
     * @return Block protocol state from ID
     */
    private String convertBlockProtocolIDtoState(int id) {
        if (id == 0) return "default";
        if (id == 1) return "never";
        if (id == 2) return "yes";
        return "default";
    }

    /**
     * Returns the plugin class
     * @return Main plugin class
     */
    public Main getPlugin() {
        return plugin;
    }

    /**
     * Returns the plugin's logger
     * @return Plugin's logger
     */
    public Logger getLogger() {
        return logger;
    }

    public boolean isRotationEnabled() {
        return rotation;
    }

    public boolean isHidePlayersEnabled() {
        return hidePlayers;
    }

    public boolean isCheckUpdatesEnabled() {
        return checkUpdates;
    }

    public boolean isAutoUpdateEnabled() {
        return autoUpdate;
    }

    public boolean areMetricsEnabled() {
        return metrics;
    }

    public List<String> getMotDs() {
        return motDs;
    }

    public String getVersionText() {
        return versionText;
    }

    public int getBlockProtocolID() {
        return blockProtocol;
    }

    public String getMotD() {
        return motD;
    }

    public void setMotD(String motD) {
        this.motD = motD;
    }

    public void setMotDs(List<String> motDs) {
        this.motDs = motDs;
        config.set("motds", motDs);
        plugin.saveConfig();
    }
}
