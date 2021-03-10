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
    Plugin plugin;
    public PingEvent(Main plugin) {
        this.plugin = plugin;
    }

    private String getRandomMotd() throws Exception {
        int n = (int) (Math.random() * (Objects.requireNonNull(plugin.getConfig().getList("rotating-motds")).size()));
        if ( plugin.getConfig().getList("rotating-motds").get(n) instanceof List) {
            // (List<String>) plugin.getConfig().getList("rotating-motds").get(n);
            Gson gson = new Gson();
            List<String> randomMotd = gson.fromJson(gson.toJson(Objects.requireNonNull(plugin.getConfig().getList("rotating-motds")).get(n)), new TypeToken<List<String>>(){}.getType());
            if (randomMotd.size() != 2) throw new Exception("There is an error in your config.yml file");
            String refractoredMotd = randomMotd.get(0) + "\n" + randomMotd.get(1);
            return refractoredMotd;
        }
        else throw new Exception("There is an error in your config.yml file");
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
        if (plugin.getConfig().getBoolean("rotation") && Objects.requireNonNull(plugin.getConfig().getList("rotating-motds")).size() != 0) {
            try {
                String randomMotd = getRandomMotd();
                plugin.getLogger().info(randomMotd);
                e.setMotd(randomMotd);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
