package dev.chechu.motdchanger;

import org.apache.logging.log4j.util.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class command implements CommandExecutor {
    private final paper plugin;
    public command(paper paper) {
        plugin = paper;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        MotD motD = new MotD(plugin,player);
        if(args.length < 1) return false;
        switch (args[0]) {
            case "help" -> {
                getHelp(player);
                return true;
            }
            case "info" -> {
                getInfo(player);
                return true;
            }
            case "motd" -> {
                switch (args[1]) {
                    case "get" -> {
                        player.sendMessage(motD.getMotD());
                        return true;
                    }
                    case "set" -> {
                        switch (args[2]) {
                            case "permanent" -> {
                                player.sendMessage("Message of the Day successfully changed.");
                                return motD.setMotD(getArgs(3, args), true);
                            }
                            case "temporary" -> {
                                player.sendMessage("Message of the Day successfully changed.");
                                return motD.setMotD(getArgs(3, args), false);
                            }
                            default -> {
                                return false;
                            }
                        }
                    }
                    case "clear" -> {
                        player.sendMessage("Message of the Day cleared successfully.");
                        return motD.setMotD();
                    }
                    default -> {
                        return false;
                    }
                }
                }
                case "protocol" -> {
                    switch (args[1]) {
                        case "display" -> {
                            switch (args[2]) {
                                case "default" -> {
                                    player.sendMessage("Protocol will be displayed as normal.");
                                    return true;
                                }
                                case "never" -> {
                                    player.sendMessage("Protocol will always be fine, without depending on client's and server's version.");
                                    return true;
                                }
                                case "always" -> {
                                    player.sendMessage("Protocol will always display Incompatible version and so it will show the Version Name.");
                                    return true;
                                }
                                default -> {
                                    player.sendMessage("Protocol is shown <x>.");
                                    return false;
                                }
                            }
                        }
                        case "set" -> {
                            player.sendMessage("Protocol text is set to <x>");
                            return true;
                        }
                        case "players" -> {
                            switch (args[3]) {
                                case "show" -> {
                                    player.sendMessage("Players will now be shown.");
                                    return true;
                                }
                                case "hide" -> {
                                    player.sendMessage("Players will now be hidden.");
                                    return true;
                                }
                                default -> {
                                    return false;
                                }
                            }
                        }
                        default -> {
                            return false;
                        }
                    }
                }
            default -> {
                return false;
            }
        }

    }
    private void getHelp(Player player) {

    }
    private void getInfo(Player player) {

    }
    private String getArgs(int from, String[] args){
        return Strings.join(Arrays.asList(args).subList(from,args.length-1), ' ');
    }
}