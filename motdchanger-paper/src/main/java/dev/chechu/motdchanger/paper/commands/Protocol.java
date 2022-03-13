package dev.chechu.motdchanger.paper.commands;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.commands.CommandManager;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Sender;
import dev.chechu.motdchanger.paper.commands.protocol.Display;
import dev.chechu.motdchanger.paper.commands.protocol.Players;
import dev.chechu.motdchanger.paper.commands.protocol.Set;

import java.util.Collections;
import java.util.List;

public class Protocol implements Command {
    @Override
    public void execute(Sender<?> sender, String[] args, CommandManager<?> commandManager) {
        sender.sendMessage(commandManager.getHelpManager().getHelp(this,true));
    }

    @Override
    public Description getDescription() {
        return new Description("protocol","Manages the server's protocol", Collections.emptyList(), List.of(new Display(), new Players(), new Set()));
    }
}
