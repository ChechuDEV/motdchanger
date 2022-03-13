package dev.chechu.motdchanger.paper.commands;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.commands.CommandManager;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Sender;
import dev.chechu.motdchanger.paper.Configuration;
import dev.chechu.motdchanger.paper.MotD;
import dev.chechu.motdchanger.paper.commands.motd.Clear;
import dev.chechu.motdchanger.paper.commands.motd.Get;
import dev.chechu.motdchanger.paper.commands.motd.Set;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class Motd implements Command {
    @Override
    public void execute(Sender<?> sender, String[] strings, CommandManager<?> manager) {

    }

    @Override
    public Description getDescription() {
        return new Description("motd","Manages motd", Collections.emptyList(),List.of(new Get(), new Set(), new Clear()));
    }
}