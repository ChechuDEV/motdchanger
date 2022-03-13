package dev.chechu.motdchanger.paper.commands;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.commands.CommandManager;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Sender;
import dev.chechu.motdchanger.paper.Configuration;
import dev.chechu.motdchanger.paper.MotD;
import dev.chechu.motdchanger.paper.commands.motd.Get;
import dev.chechu.motdchanger.paper.commands.motd.Set;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class Motd implements Command {
    private CommandManager manager;
    private Configuration configuration;
    private MotD motDManager;
    public Motd(CommandManager manager) {
        this.manager = manager;
        configuration = (Configuration) manager.getConfig();
        motDManager = configuration.getMotDManager();
    }

    @Override
    public void execute(Sender<?> sender, String[] strings) {

    }

    @Override
    public Description getDescription() {
        return new Description("motd","Manages motd", Collections.emptyList(),List.of(new Get(manager), new Set(motDManager)));
    }
}