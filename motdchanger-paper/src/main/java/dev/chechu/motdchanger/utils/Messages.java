package dev.chechu.motdchanger.utils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.awt.*;

public class Messages {
    public void sendMessage(Player player, String string) {
        player.sendMessage(ChatColor.GREEN + "MOTDChanger » " + string);
    }
    public void sendMessage(Player player, String string, boolean inline) {
        if (inline) player.sendMessage(string);
        else sendMessage(player, string);
    }

    public void sendMessage(Player player, Message message) {
        BaseComponent messageText = new TextComponent(message.level.color + "MotDChanger » " + message.message);
        if(message.hover != null) {
            Text contents = new Text(message.hover);
            HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, contents);
        }
    }
}
