package dev.chechu.motdchanger.paper.commands;

import dev.chechu.dragonapi.core.commands.Command;
import dev.chechu.dragonapi.core.commands.CommandManager;
import dev.chechu.dragonapi.core.commands.HelpManager;
import dev.chechu.dragonapi.core.utils.Description;
import dev.chechu.dragonapi.core.utils.Sender;

public class Help implements Command {
    CommandManager manager;
    HelpManager helpManager;
    public Help(CommandManager manager) {
        this.manager = manager;
        helpManager = new HelpManager("motdchange",manager);
    }

    @Override
    public void execute(Sender<?> sender, String[] strings) {
        sender.sendMessage(helpManager.getAllHelp());
    }

    @Override
    public Description getDescription() {
        return null;
    }
}
