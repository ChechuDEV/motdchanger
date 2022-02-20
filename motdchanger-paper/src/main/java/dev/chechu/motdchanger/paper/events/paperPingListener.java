package dev.chechu.motdchanger.paper.events;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import dev.chechu.motdchanger.paper.Configuration;
import dev.chechu.motdchanger.paper.MotD;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

public class paperPingListener implements Listener {
    private final Configuration config;
    private final MotD motD;
    public paperPingListener(Configuration config) {
        this.config = config;
        motD = new MotD(config);
    }

    @EventHandler
    public void onServerListPing(PaperServerListPingEvent event) {
        event.motd(Component.text(motD.getMotD()));
        event.setVersion(config.getVersionText());
        if(Objects.equals(config.getBlockProtocolID(), 1)) event.setProtocolVersion(event.getProtocolVersion());
        else if (Objects.equals(config.getBlockProtocolID(), 2)) event.setProtocolVersion(-1);
        event.setHidePlayers(config.isHidePlayersEnabled());
    }
}
