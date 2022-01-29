package dev.chechu.motdchanger.utils;

import org.bukkit.ChatColor;

public enum Level {
    ERROR(ChatColor.DARK_RED),
    WARNING(ChatColor.RED),
    SUCCESS(ChatColor.GREEN),
    INFO(ChatColor.AQUA);

    final ChatColor color;

    Level(ChatColor color) {
        this.color = color;
    }
}
