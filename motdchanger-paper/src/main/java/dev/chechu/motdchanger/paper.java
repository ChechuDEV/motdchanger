package dev.chechu.motdchanger;

import dev.chechu.motdchanger.events.bukkitPingListener;
import dev.chechu.motdchanger.events.packetPingListener;
import dev.chechu.motdchanger.events.paperPingListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class paper extends JavaPlugin {
    private Logger log;
    @Override
    public void onEnable() {
        log = getLogger();
        log.info("Thanks for using my plugin! Remember to rate it and share it with your friends!");

        // Server check
        boolean isPaper = false;
        boolean hasProtocol = false;
        try {
            Class.forName("com.destroystokyo.paper.ParticleBuilder");
            isPaper = true;
        } catch (ClassNotFoundException e) {}
        try {
            Class.forName("com.comphenix.protocol.wrappers.WrappedServerPing");
            hasProtocol = true;
        } catch (ClassNotFoundException e) {}
        if (isPaper) {
            getServer().getPluginManager().registerEvents(new paperPingListener(),this);
        } else {
            if (hasProtocol) {
                new packetPingListener(this);
            } else {
                getServer().getPluginManager().registerEvents(new bukkitPingListener(),this);
            }
        }
        // Command init
        //PluginCommand MOTDChange = getCommand("motdchange");
        //MOTDChange.setExecutor(new command());
        // Event init
        // Config
        // Metrics
        // Autoupdate
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
