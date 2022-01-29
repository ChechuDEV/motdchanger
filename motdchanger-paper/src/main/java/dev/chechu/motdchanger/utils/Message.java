package dev.chechu.motdchanger.utils;

import org.bukkit.ChatColor;

public enum Message {
    PERMISSION(Level.ERROR, "You don't have enough permissions to execute this command.", ChatColor.RED + "Required permission: " + ChatColor.GRAY + "%s"),
    PERMANENT_SUCCESS(Level.SUCCESS, "Permanent Message of the Day successfully set.", "%s"),
    TEMPORARY_SUCCESS(Level.SUCCESS, "Temporary Message of the Day successfully set.", "%s"),
    ;

    final Level level;
    final String message;
    String hover;

    Message(Level level, String message) {
        this.level = level;
        this.message = message;
    }

    Message(Level level, String message, String hover) {
        this.level = level;
        this.message = message;
        this.hover = hover;
    }
}
