package dev.tsetsi.motdchanger.bukkit;

import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class Motd {
    Plugin plugin;
    String motd = "";
    public Motd(Plugin plugin) {
        this.plugin = plugin;
        this.motd = getPermanent();
    }

    public void setMotd(String text, int line, boolean permanent) {
        // DO EITHER PERMANENT OR TEMPORARY?
        String motd = getMotd();
        String[] splitMotd = motd.split("%newline%");
        StringBuilder newMotd = new StringBuilder();
        if(line == 2){
            if(splitMotd.length >= 2) newMotd.append(splitMotd[0]);
            newMotd.append("%newline%");
        }
        newMotd.append(fixColors(text));
        if (line == 1){
            newMotd.append("%newline%");
            if(splitMotd.length >= 2) newMotd.append(splitMotd[1]);
        }
        if(permanent) setPermanent(newMotd.toString()); else setTemporary(newMotd.toString());
    }

    public void setPermanent(String motd) {
        if(motd.equals("%motdchangerpermanent%")) this.motd = getPermanent();
        else {
            plugin.getConfig().set("permanent-motd", motd);
            this.motd = motd;
        }
    }

    public String getPermanent() {
        return plugin.getConfig().getString("permanent-motd").replace("%newline%","\n");
    }

    public void setTemporary(String motd) {
        this.motd = motd;
    }

    public String getMotd() {
        return this.motd;
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
