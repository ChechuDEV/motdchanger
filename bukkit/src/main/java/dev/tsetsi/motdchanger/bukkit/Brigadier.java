package dev.tsetsi.motdchanger.bukkit;

import com.mojang.brigadier.tree.LiteralCommandNode;
import me.lucko.commodore.Commodore;
import me.lucko.commodore.CommodoreProvider;
import me.lucko.commodore.file.CommodoreFileFormat;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;

import java.io.InputStream;

public final class Brigadier {
    public Brigadier() {
    }

    public static void register(Main plugin, Commodore commodore, PluginCommand command) throws Exception {
        try ( InputStream is = plugin.getResource("motdchange.commodore")) {
            if ( is == null ) throw new Exception("Brigadier command data missing from jar");
            LiteralCommandNode<?> commandNode = CommodoreFileFormat.parse(is);
            commodore.register(command, commandNode);
        }
    }
}
