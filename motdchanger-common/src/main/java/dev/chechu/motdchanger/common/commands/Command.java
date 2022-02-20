package dev.chechu.motdchanger.common.commands;

import dev.chechu.motdchanger.common.utils.Sender;
import lombok.Getter;

import java.util.List;

public abstract class Command {
    @Getter String description;
    @Getter List<String> params;
    @Getter String command;
    @Getter List<Command> subcommands;

    public Command(String description, List<String> params, String command, List<Command> subcommands) {
        this.description = description;
        this.params = params;
        this.command = command;
        this.subcommands = subcommands;
    }

    /**
     * Executes the command's code
     * @param sender Sender of the command
     * @param args Arguments of the command
     */
    public abstract void execute(Sender sender, String[] args);

    /**
     * Checks if the command has subcommands
     * @return if Subcommands exist
     * @deprecated Pending of removal
     */
    @Deprecated
    public boolean hasSubcommands() {
        return !subcommands.isEmpty();
    }
}
