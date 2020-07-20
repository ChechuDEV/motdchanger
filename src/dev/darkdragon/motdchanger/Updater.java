package dev.darkdragon.motdchanger;

import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Updater {
    motdchanger plugin;
    String newVersion;
    URL checkUrl;
    public Updater(motdchanger motdchanger) {
        plugin = motdchanger;
        if(plugin.getConfig().getBoolean("checkupdates")) {
            try {
                checkUrl = new URL("https://api.spigotmc.org/legacy/update.php?resource=63607");
                checkUpdates();
                if (!newVersion.equals(plugin.getDescription().getVersion())) {
                    plugin.sendMessage(ChatColor.YELLOW + "A new update is available! Version: "+newVersion);
                }
            } catch (IOException e) {
                plugin.sendMessage(ChatColor.RED + "Couldn't connect to Spigot, no updates available");
                e.printStackTrace();
            }
        }
    }

    public void checkUpdates() throws IOException {
        URLConnection con = checkUrl.openConnection();
        newVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
    }

}
