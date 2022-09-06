package dev.chechu.motdchanger;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import dev.chechu.dragonapi.spigot.SpigotUpdater;
import dev.chechu.motdchanger.bukkit.BukkitPingListener;
import dev.chechu.motdchanger.exceptions.EmptyListException;

public class BukkitPlugin extends JavaPlugin {
    private static MOTDManager manager;

    public static MOTDManager getMOTDManager() {
        return manager;
    }

    @Override
    public void onEnable() {
        getLogger().info("Thanks for using MOTDChanger. Made by https://chechu.dev/");

        new SpigotUpdater(this, "63607", false);

        saveDefaultConfig();

        manager = new MOTDManager((manager) -> {
            getConfig().set("motds", manager.serializeAllMOTD());
            getConfig().set("rotation", manager.isRotation());
            saveConfig();
        });

        reloadConfig();

        if (isPaper()) {
            initPaper();
        } else {
            initBukkit();
        }
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        try {
            manager.reload(getConfig().getStringList("motds"), getConfig().getBoolean("rotation"));
        } catch (EmptyListException e) {
            getLogger().warning("No MotDs detected! Plugin will not work as expected.");
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
        getLogger().info("Thanks for using MOTDChanger. Made by https://chechu.dev/");
    }
}
