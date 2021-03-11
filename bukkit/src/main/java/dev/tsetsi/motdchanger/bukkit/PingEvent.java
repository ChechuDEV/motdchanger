package dev.tsetsi.motdchanger.bukkit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PingEvent implements Listener {
    Motd motd;
    public PingEvent(Motd motd) {
        this.motd = motd;
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
        e.setMotd(motd.getMotd());
    }
}
