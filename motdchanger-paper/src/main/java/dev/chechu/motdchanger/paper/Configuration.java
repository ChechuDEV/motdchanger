package dev.chechu.motdchanger.paper;

import dev.chechu.dragonapi.core.utils.ConfigChunk;
import dev.chechu.dragonapi.spigot.SpigotConfig;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.FileUtil;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

public class Configuration extends SpigotConfig {
    @Getter private final ConfigChunk<Boolean> rotation = new ConfigChunk<>("rotation",false);
    @Getter private final ConfigChunk<Boolean> hidePlayers = new ConfigChunk<>("hide-players", false);
    @Getter private final ConfigChunk<Boolean> checkUpdates = new ConfigChunk<>("check-updates", true);
    @Getter private final ConfigChunk<Boolean> autoUpdate = new ConfigChunk<>("autoupdate", true);
    @Getter private final ConfigChunk<Boolean> metrics = new ConfigChunk<>("metrics", true);
    @Getter private final ConfigChunk<Boolean> debugMode = new ConfigChunk<>("debug-mode", false);
    @Getter private final ConfigChunk<List<String>> motDs = new ConfigChunk<>("motds", List.of("&bThe server is working smoothly%newline%&aBe happy! ^^"));
    @Getter private final ConfigChunk<String> versionText = new ConfigChunk<>("version-text", "&4Maintenance!");
    @Getter private final ConfigChunk<String> blockProtocol = new ConfigChunk<>("block-protocol","default");


    @Getter private final MotD motDManager;

    public Configuration(JavaPlugin plugin) {
        super(plugin.getLogger(), plugin);
        this.motDManager = new MotD(this);
    }

    @Override
    public void initializeConfig() {
        getConfigChunks().add(rotation);
        getConfigChunks().add(hidePlayers);
        getConfigChunks().add(checkUpdates);
        getConfigChunks().add(autoUpdate);
        getConfigChunks().add(metrics);
        getConfigChunks().add(debugMode);
        getConfigChunks().add(motDs);
        getConfigChunks().add(versionText);
        getConfigChunks().add(blockProtocol);
    }

    @Override
    public void extraReloadConfig() {

    }


    public void setMotDs(List<String> motDs) {
        this.motDs.setValue(motDs);
        getConfig().set("motds", motDs);
        getPlugin().saveConfig();
    }

    public String getMotD() {
        return motDManager.getMotD();
    }
}
