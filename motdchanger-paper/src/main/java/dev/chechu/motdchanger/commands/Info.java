package dev.chechu.motdchanger.commands;

import dev.chechu.motdchanger.utils.Message;
import dev.chechu.motdchanger.utils.Messages;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class Info extends Command {
    public Info() {
        super("Displays information about this plugin", Collections.emptyList(), "info", Collections.emptyList());
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Messages.sendMessage(sender, Message.INFO);
    }
}
