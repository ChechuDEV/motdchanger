package dev.chechu.motdchanger;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import dev.chechu.dragonapi.spigot.SpigotUpdater;
import dev.chechu.motdchanger.bukkit.BukkitPingListener;

public class Main extends JavaPlugin {
    private static MOTDManager manager;

    public static MOTDManager getManager() {
        return manager;
    }

    @Override
    public void onEnable() {
        new SpigotUpdater(this, "63607", false);

        manager = new MOTDManager((manager) -> {
            getConfig().set("motds", manager.serializeAllMOTD());
            getConfig().set("rotation", manager.isRotation());
            saveConfig();
        });
        if (isPaper()) {
            initPaper();
        } else {
            initBukkit();
        }
    }

    private void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    private void initPaper() {
        initBukkit();
    }

    private void initBukkit() {
        registerListener(new BukkitPingListener());
    }

    private boolean isPaper() {
        try {
            Class.forName("com.destroystokyo.paper.ParticleBuilder");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public void onDisable() {
    }
}
