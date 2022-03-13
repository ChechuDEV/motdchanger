package dev.chechu.motdchanger.paper.commands.motd.set;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.commands.CommandManager;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Message;
import dev.chechu.dragonapi.core.utils.Sender;
import dev.chechu.motdchanger.paper.Configuration;
import dev.chechu.motdchanger.paper.MotD;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;

public class Permanent implements Command {
    @Override
    public void execute(Sender<?> sender, String[] strings, CommandManager<?> manager) {
        if (((Configuration)manager.getConfig()).getMotDManager().setMotD(Strings.join(Arrays.asList(strings), ' '), true)) {
            sender.sendMessage("MotdChanger >> " + Message.get("en_US","motdchange-set-permanent-success"));
        }
    }

    @Override
    public Description getDescription() {
        return null;
    }
}
