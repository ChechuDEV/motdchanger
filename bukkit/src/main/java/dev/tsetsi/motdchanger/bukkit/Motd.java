package dev.tsetsi.motdchanger.bukkit;

import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class Motd {
    Plugin plugin;
    String temporaryMotd = "";
    public Motd(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setMotd(String text, int line, boolean permanent) {
        String motd = permanent ? getPermanent() : getTemporary();
        String[] splitMotd = motd.split("\n");
        StringBuilder newMotd = new StringBuilder();
        if(line == 2){
            if(splitMotd.length >= 2) newMotd.append(splitMotd[0]);
            newMotd.append("\n");
        }
        newMotd.append(fixColors(text));
        if (line == 1){
            newMotd.append("\n");
            if(splitMotd.length >= 2) newMotd.append(splitMotd[1]);
        }
        if(permanent) setPermanent(newMotd.toString()); else setTemporary(newMotd.toString());
    }

    public void setPermanent(String motd) {
        plugin.getConfig().set("permanent-motd", motd);
    }

    public String getPermanent() {
        return plugin.getConfig().getString("permanent-motd");
    }

    public void setTemporary(String motd) {
        temporaryMotd = motd;
    }

    public String getTemporary() {
        return temporaryMotd;
    }

    // Replaces all the colors with & symbol to § symbol ones without replacing all the & (Because they may mean AND instead of Color)
    public String fixColors(String text) {
        return text.replace("&0","§0")
                .replace("&1","§1")
                .replace("&2","§2")
                .replace("&3","§3")
                .replace("&4","§4")
                .replace("&5","§5")
                .replace("&6","§6")
                .replace("&7","§7")
                .replace("&8","§8")
                .replace("&9","§9")
                .replace("&a","§a")
                .replace("&b","§b")
                .replace("&c","§c")
                .replace("&d","§d")
                .replace("&e","§e")
                .replace("&f","§f")
                .replace("&g","§g")
                .replace("&k","§k")
                .replace("&l","§l")
                .replace("&m","§m")
                .replace("&n","§n")
                .replace("&o","§o")
                .replace("&r","§r");
    }
}
