package dev.tsetsi.motdchanger.bukkit;

import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class MOTD {
    Plugin plugin;
    String mOTD;
    public MOTD(Plugin plugin) {
        this.plugin = plugin;
        this.mOTD = getPermanent();
    }

    //  * SETTERS

    // SET PERMANENT OR TEMPORARY MOTD
    public void setMOTD(String text, int line, boolean permanent) {
        String mOTD = permanent ? getPermanent() : getMOTD();
        String[] splitMOTD = mOTD.split("%newline%");
        StringBuilder newMOTD = new StringBuilder();
        if(line == 2){
            if(splitMOTD.length >= 2) newMOTD.append(splitMOTD[0]);
            newMOTD.append("%newline%");
        }
        newMOTD.append(fixColors(text));
        if (line == 1){
            newMOTD.append("%newline%");
            if(splitMOTD.length >= 2) newMOTD.append(splitMOTD[1]);
        }
        if (permanent) setPermanent(newMOTD.toString());
        else setTemporary(newMOTD.toString());
    }

    // SET PERMANENT MOTD
    public void setPermanent(String mOTD) {
        if(mOTD.equals("%motdchangerpermanent%")) this.mOTD = getPermanent();
        else {
            plugin.getConfig().set("permanent-motd", mOTD);
            this.mOTD = mOTD;
        }
    }

    // SET TEMPORARY MOTD
    public void setTemporary(String mOTD) {
        this.mOTD = mOTD;
    }

    //  * GETTERS

    // GET PERMANENT MOTD
    public String getPermanent() {
        return Objects.requireNonNull(plugin.getConfig().getString("permanent-motd")).replace("%newline%","\n");
    }

    // GET CURRENT MOTD
    public String getMOTD() {
        return this.mOTD;
    }

    //  * ENHANCERS
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
