package dev.chechu.motdchanger.events;

import com.destroystokyo.paper.event.player.IllegalPacketEvent;
import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class paperPingListener implements Listener {
    @EventHandler
    public void onServerListPing(PaperServerListPingEvent event) {
        event.setVersion("§3test");
        event.setProtocolVersion(10);
        event.setNumPlayers(15);
        event.setMaxPlayers(2);
        Component textComponent = Component.text("Test\nTest2");
        event.motd(textComponent);
    }
}
