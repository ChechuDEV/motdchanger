package dev.chechu.motdchanger.paper.commands.motd;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.commands.CommandManager;
import dev.chechu.dragonapi.core.commands.HelpManager;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Sender;
import dev.chechu.motdchanger.paper.MotD;
import dev.chechu.motdchanger.paper.commands.Help;
import dev.chechu.motdchanger.paper.commands.motd.set.Permanent;
import dev.chechu.motdchanger.paper.commands.motd.set.Temporary;

import java.util.Collections;
import java.util.List;

public class Set implements Command {
    @Override
    public void execute(Sender<?> sender, String[] strings, CommandManager<?> manager) {
        sender.sendMessage(manager.getHelpManager().getHelp(this,true));
    }

    @Override
    public Description getDescription() {
        return new Description("set","", Collections.emptyList(), List.of(new Permanent(), new Temporary()));
    }
}
