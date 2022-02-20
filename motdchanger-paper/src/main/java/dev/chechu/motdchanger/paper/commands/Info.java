package dev.chechu.motdchanger.paper.commands;

import dev.chechu.motdchanger.paper.utils.Message;
import dev.chechu.motdchanger.paper.utils.Messages;
import org.bukkit.command.CommandSender;

import java.util.Collections;

public class Info extends Command {
    public Info() {
        super("Displays information about this plugin", Collections.emptyList(), "info", Collections.emptyList());
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Messages.sendMessage(sender, Message.INFO);
    }
}
