package dev.chechu.motdchanger.events;

import com.destroystokyo.paper.event.player.IllegalPacketEvent;
import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import dev.chechu.motdchanger.MotD;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class paperPingListener implements Listener {
    private final MotD motD;
    public paperPingListener(Plugin pl) {
        motD = new MotD(pl);
    }

    @EventHandler
    public void onServerListPing(PaperServerListPingEvent event) {
        event.motd(Component.text(motD.getMotD()));
        event.setVersion(motD.getVersionName());
        if(Objects.equals(motD.getProtocol(), "never")) event.setProtocolVersion(event.getProtocolVersion());
        else if (Objects.equals(motD.getProtocol(), "yes")) event.setProtocolVersion(-1);
        event.setHidePlayers(motD.hidePlayers());
    }
}
