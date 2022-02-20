package dev.chechu.motdchanger.paper;

import dev.chechu.motdchanger.paper.commands.MainCommand;
import dev.chechu.motdchanger.paper.events.bukkitPingListener;
import dev.chechu.motdchanger.paper.events.packetPingListener;
import dev.chechu.motdchanger.paper.events.paperPingListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    private Logger log;
    public String motD;

    private Configuration config = null;
    @Override
    public void onEnable() {
        log = getLogger();
        log.info("Thanks for using my plugin! Remember to rate it and share it with your friends!");

        config = new Configuration(this);

        // Event hook
        eventHook();

        // Command init
        PluginCommand motDChange = getCommand("motdchange");
        assert motDChange != null;
        motDChange.setExecutor(new MainCommand(config));

        // Config file set up
        File configFile = new File(getDataFolder(),"config.yml");
        if (!configFile.exists() || !getConfig().contains("version")) {
            saveDefaultConfig();
        } // TODO: CONFIG FILE VERSION IS USELESS FOR NOW

        // Metrics
        if(config.isMetrics())
            new Metrics(this, 4679);


        motD = config.getMotD();
        // TODO: Autoupdate
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
            log.info("Initializing Paper Ping Listener");
        }
        else if (hasProtocol()) {
            new packetPingListener(config);
            log.info("Initializing ProtocolLib Ping Packet Listener");
        }
        else {
            getServer().getPluginManager().registerEvents(new bukkitPingListener(config),this);
            log.warning("It seems that you aren't using neither Paper nor ProtocolLib, this plugin will be limited but will still work.");
            log.info("Initializing Bukkit Ping Listener");
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