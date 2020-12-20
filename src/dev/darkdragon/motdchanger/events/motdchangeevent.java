package dev.darkdragon.motdchanger.events;

import dev.darkdragon.motdchanger.motdchanger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;

public class motdchangeevent implements Listener {
    motdchanger plugin;

    public motdchangeevent(motdchanger pl) {
        plugin = pl;
    }

    int length = 0;
    public void getLength() {
        File motds = new File(plugin.getDataFolder(), "motds.yml");
        FileConfiguration motdsFile = YamlConfiguration.loadConfiguration(motds);

        length = 0;
        for ( int i = 1; i <= 10; i++){
            if (!motdsFile.getString("motd-rotation" + i).equals("")){
                length = i;
            } else {
                break;
            }
        }
    }
    public String getRotation() {
        File motds = new File(plugin.getDataFolder(), "motds.yml");
        FileConfiguration motdsFile = YamlConfiguration.loadConfiguration(motds);
        getLength();
        int n = (int) (Math.random() * (length - 1 + 1) + 1);
        return motdsFile.getString("motd-rotation"+n);
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
        getLength();
        if (plugin.getConfig().getBoolean("rotation") && length != 0){
            e.setMotd(getRotation().replace("%newline%","\n").replace("&","§"));
        }else {
            e.setMotd(plugin.Motd.replace("%newline%","\n").replace("&","§"));
        }
    }
}
