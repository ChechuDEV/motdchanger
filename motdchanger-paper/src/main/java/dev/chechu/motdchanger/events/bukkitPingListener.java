package dev.chechu.motdchanger.events;

import dev.chechu.motdchanger.MotD;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;

public class bukkitPingListener implements Listener {
    private final MotD motD;
    public bukkitPingListener(Plugin pl) {
        motD = new MotD(pl);
    }

    @EventHandler
    private void onServerListPing(ServerListPingEvent event) {
        event.motd(Component.text(motD.getMotD()));
    }
}
