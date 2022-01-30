package dev.chechu.motdchanger.utils;

import dev.chechu.motdchanger.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.ChatColor;

public enum Message {
    INFO(Level.INFO, "This plugin has been made by Chechu (at chechu.dev).", ChatColor.AQUA + "Version " + Main.getVersion(), "https://chechu.dev/", ClickEvent.Action.OPEN_URL),
    PERMISSION(Level.ERROR, "You don't have enough permissions to execute this command.", ChatColor.RED + "Required permission: " + ChatColor.GRAY + "%s"),
    PERMANENT_SUCCESS(Level.SUCCESS, "Permanent Message of the Day successfully set.", "%s"),
    TEMPORARY_SUCCESS(Level.SUCCESS, "Temporary Message of the Day successfully set.", "%s"),
    ;

    final Level level;
    final String message;
    String hover;
    String click;
    ClickEvent.Action clickAction;

    Message(Level level, String message) {
        this.level = level;
        this.message = message;
    }

    Message(Level level, String message, String hover) {
        this.level = level;
        this.message = message;
        this.hover = hover;
    }

    Message(Level level, String message, String hover, String click, ClickEvent.Action clickAction) {
        this.level = level;
        this.message = message;
        this.hover = hover;
        this.click = click;
        this.clickAction = clickAction;
    }
}
