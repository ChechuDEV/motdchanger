package dev.chechu.motdchanger.paper.commands.motd;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.commands.CommandManager;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Message;
import dev.chechu.dragonapi.core.utils.Sender;
import dev.chechu.motdchanger.paper.Configuration;
import dev.chechu.motdchanger.paper.MotD;

import java.util.Collections;

public class Clear implements Command {
    @Override
    public void execute(Sender<?> sender, String[] strings, CommandManager<?> manager) {
        if (((Configuration)manager.getConfig()).getMotDManager().setMotD()) {
            sender.sendMessage(Message.get(sender.getLocale(),"motdchange-clear-success"));
        }
    }

    @Override
    public Description getDescription() {
        return new Description("clear","Clears the static motd", Collections.emptyList(), Collections.emptyList());
    }
}
