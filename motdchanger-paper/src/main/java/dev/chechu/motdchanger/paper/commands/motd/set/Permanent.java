package dev.chechu.motdchanger.paper.commands.motd.set;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Sender;
import dev.chechu.motdchanger.paper.MotD;
import dev.chechu.motdchanger.paper.utils.Message;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;

public class Permanent implements Command {
    private MotD motDManager;

    public Permanent(MotD motDManager) {
        this.motDManager = motDManager;
    }

    @Override
    public void execute(Sender<?> sender, String[] strings) {
        if (motDManager.setMotD(Strings.join(Arrays.asList(strings), ' '), true)) {
            messages.sendMessage(player, Message.PERMANENT_SUCCESS);
        }
    }

    @Override
    public Description getDescription() {
        return null;
    }
}
