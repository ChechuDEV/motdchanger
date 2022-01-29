package dev.chechu.motdchanger.events;

import dev.chechu.motdchanger.Configuration;
import dev.chechu.motdchanger.MotD;
import dev.chechu.motdchanger.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class bukkitPingListener implements Listener {
    private final Configuration config;
    private final MotD motD;
    public bukkitPingListener(Configuration config) {
        this.config = config;
        motD = new MotD(config);
    }

    @EventHandler
    private void onServerListPing(ServerListPingEvent event) {
        event.setMotd(motD.getMotD());
    }
}
