package dev.tsetsi.motdchanger.bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class PingEvent implements Listener {
    MOTD motd;
    public PingEvent(MOTD motd) {
        this.motd = motd;
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
        e.setMotd(motd.getMOTD());
    }
}
