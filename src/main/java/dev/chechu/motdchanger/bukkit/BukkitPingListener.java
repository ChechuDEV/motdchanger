package dev.chechu.motdchanger.bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import dev.chechu.motdchanger.MOTDManager;
import dev.chechu.motdchanger.Main;
import net.kyori.adventure.text.Component;

public class BukkitPingListener implements Listener {
    @EventHandler
    public void onPing(ServerListPingEvent event) {
        MOTDManager manager = Main.getMOTDManager();
        Component component = manager.getMOTD();

        event.setMotd(manager.legacySerialize(component));
    }
}
