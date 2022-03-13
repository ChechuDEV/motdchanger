package dev.chechu.motdchanger.paper.commands;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Sender;
import dev.chechu.motdchanger.paper.utils.Message;
import dev.chechu.motdchanger.paper.utils.Messages;
import org.bukkit.command.CommandSender;

import java.util.Collections;

public class Info implements Command {

    public void a(CommandSender sender, String[] args) {
        Messages.sendMessage(sender, Message.INFO);
    }

    @Override
    public void execute(Sender<?> sender, String[] strings) {

    }

    @Override
    public Description getDescription() {
        return null;
    }
}
