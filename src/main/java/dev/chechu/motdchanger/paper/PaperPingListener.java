package dev.chechu.motdchanger.paper;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;

import dev.chechu.motdchanger.BukkitPlugin;

public class PaperPingListener implements Listener {
    @EventHandler
    public void onPing(PaperServerListPingEvent event) {
        event.motd(BukkitPlugin.getMOTDManager().getMOTD());
    }
}
