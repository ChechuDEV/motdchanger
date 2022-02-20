package dev.chechu.motdchanger.paper.utils;

import dev.chechu.motdchanger.common.utils.Sender;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BukkitSender extends Sender {
    private CommandSender sender;
    public BukkitSender(boolean consoleSender, boolean commandBlockSender, boolean playerSender, Object sender) {
        super(consoleSender, commandBlockSender, playerSender, sender);
        this.sender = (CommandSender) sender;
    }

    public static BukkitSender from(CommandSender sender) {
        boolean console = sender instanceof ConsoleCommandSender;
        boolean commandBlock = sender instanceof BlockCommandSender;
        boolean playerSender = sender instanceof Player;
        return new BukkitSender(console,commandBlock,playerSender,sender);
    }

    @Override
    public void sendMessage(String message) {
        sender.sendMessage(message);
    }

    @Override
    public void sendActionBar(String actionBar) {
        if(isPlayerSender()) {
            ((Player) sender).spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(actionBar));
        }
    }

    @Override
    public void sendTitle(String title, String subtitle) {
        if(isPlayerSender()) {
            ((Player) sender).sendTitle(title,subtitle);
        }
    }
}
