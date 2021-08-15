package dev.chechu.motdchanger;

import dev.chechu.motdchanger.events.bukkitPingListener;
import dev.chechu.motdchanger.events.packetPingListener;
import dev.chechu.motdchanger.events.paperPingListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class paper extends JavaPlugin {
    private Logger log;
    @Override
    public void onEnable() {
        log = getLogger();
        log.info("Thanks for using my plugin! Remember to rate it and share it with your friends!");

        // Event hook
        eventHook();

        // Command init
        PluginCommand motDChange = getCommand("motdchange");
        assert motDChange != null;
        motDChange.setExecutor(new command());

        // Config file set up
        File configFile = new File(getDataFolder(),"config.yml");
        if (!configFile.exists() || !getConfig().contains("version")) {
            saveDefaultConfig();
        } // CONFIG FILE VERSION IS USELESS FOR NOW

        // Metrics
        new Metrics(this, 4679);

        // Autoupdate
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
        if(isPaper()) getServer().getPluginManager().registerEvents(new paperPingListener(this),this);
        else if (hasProtocol()) new packetPingListener(this);
        else {
            getServer().getPluginManager().registerEvents(new bukkitPingListener(this),this);
            log.warning("It seems that you aren't using Paper nor ProtocolLib, this plugin will be limited but will still work.");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
