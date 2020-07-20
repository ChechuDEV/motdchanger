package dev.darkdragon.motdchanger.events;

import dev.darkdragon.motdchanger.motdchanger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class motdchangeevent implements Listener {
    motdchanger plugin;

    public motdchangeevent(motdchanger pl) {
        plugin = pl;
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
        e.setMotd(plugin.Motd.replace("%newline%","\n").replace("&","§"));
    }
}
