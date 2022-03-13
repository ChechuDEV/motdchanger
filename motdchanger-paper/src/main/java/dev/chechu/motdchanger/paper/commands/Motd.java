package dev.chechu.motdchanger.paper.commands;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.commands.CommandManager;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Sender;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class Motd implements Command {
    CommandManager manager;
    public Motd(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(Sender<?> sender, String[] strings) {

    }

    @Override
    public Description getDescription() {
        return null;
    }
}