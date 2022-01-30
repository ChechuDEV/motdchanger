package dev.chechu.motdchanger.utils;

import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class Messages {
    public static void sendMessage(CommandSender receiver, String string) {
        receiver.sendMessage(ChatColor.GREEN + "MOTDChanger » " + string);
    }

    public static void sendMessage(CommandSender receiver, String string, boolean inline) {
        if (inline) receiver.sendMessage(string);
        else sendMessage(receiver, string);
    }

    public static void sendMessage(CommandSender receiver, Message message) {
        BaseComponent messageText = new TextComponent(message.level.color + "MotDChanger » " + message.message);
        HoverEvent hoverEvent = null;
        if(message.hover != null) {
            Text contents = new Text(message.hover);
            hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, contents);
        }
        ClickEvent clickEvent = null;
        if(message.click != null) {
            clickEvent = new ClickEvent(message.clickAction, message.click);
        }
        if (hoverEvent != null) messageText.setHoverEvent(hoverEvent);
        if (clickEvent != null) messageText.setClickEvent(clickEvent);
        receiver.sendMessage(messageText);
    }
}
