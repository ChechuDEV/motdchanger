package dev.chechu.motdchanger.paper;

import dev.chechu.dragonapi.core.Core;
import dev.chechu.dragonapi.core.Updater;
import dev.chechu.dragonapi.spigot.SpigotUpdater;
import dev.chechu.motdchanger.paper.commands.MainCommand;
import dev.chechu.motdchanger.paper.events.bukkitPingListener;
import dev.chechu.motdchanger.paper.events.packetPingListener;
import dev.chechu.motdchanger.paper.events.paperPingListener;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    public String motD;

    private Configuration config = null;
    @Override
    public void onEnable() {
        new Core(false);
        config = new Configuration(this);
        config.getLogger().info("Thanks for using my plugin! Remember to rate it and share it with your friends!");

        // Dragon-API updater
        Updater updater = new SpigotUpdater(this, "", config.getAutoUpdate().getValue());
        Bukkit.getScheduler().runTaskTimer(this, () -> updater.tryUpdate(false), 0, 36000L);

        // Event hook
        eventHook();

        // Command init
        PluginCommand motDChange = getCommand("motdchange");
        assert motDChange != null;
        motDChange.setExecutor(new MainCommand(config));

        // Metrics
        if(config.getMetrics().getValue())
            new Metrics(this, 4679);


        motD = config.getMotD();
    }

    private boolean isPaper() {
        try {
            Class.forName("com.destroystokyo.paper.ParticleBuilder");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private boolean hasProtocol() {
        try {
            Class.forName("com.comphenix.protocol.wrappers.WrappedServerPing");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private void eventHook() {
        if(isPaper()) {
            getServer().getPluginManager().registerEvents(new paperPingListener(config), this);
            config.getLogger().info("Initializing Paper Ping Listener");
        }
        else if (hasProtocol()) {
            new packetPingListener(config);
            config.getLogger().info("Initializing ProtocolLib Ping Packet Listener");
        }
        else {
            getServer().getPluginManager().registerEvents(new bukkitPingListener(config),this);
            config.getLogger().warning("It seems that you aren't using neither Paper nor ProtocolLib, this plugin will be limited but will still work.");
            config.getLogger().info("Initializing Bukkit Ping Listener");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String getVersion() {
        return getPlugin(Main.class).getDescription().getVersion();
    }
}
