package dev.chechu.motdchanger.paper.commands;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.commands.CommandManager;
import dev.chechu.dragonapi.spigot.utils.SpigotSender;
import dev.chechu.motdchanger.paper.Configuration;
import dev.chechu.motdchanger.paper.MotD;
import org.apache.logging.log4j.util.Strings;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;

public class MainCommand implements CommandExecutor {
    private final Configuration config;
    public MainCommand(Configuration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        CommandManager<Configuration> commandManager = new CommandManager<>(config,"motdchange");
        Command help = new Help();
        commandManager.addCommand(help);
        commandManager.addCommand(new Info());
        commandManager.addCommand(new Motd());
        commandManager.execute(SpigotSender.from(sender),args,help);

        Player player = (Player) sender;
        MotD motD = new MotD(config);
        return true;
    }

    private String getArgs(int from, String[] args){
        return Strings.join(Arrays.asList(args).subList(from,args.length-1), ' ');
    }

}