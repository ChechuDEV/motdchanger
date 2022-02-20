package dev.chechu.motdchanger.common.commands;

import dev.chechu.motdchanger.common.Configuration;
import dev.chechu.motdchanger.common.utils.Sender;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager {
    @Getter private final List<Command> commands = new ArrayList<>();
    @Getter private final Configuration config;

    public CommandManager(Configuration config) {
        this.config = config;
    }

    /**
     * Adds command to the manager
     * @param command Command to be added
     */
    public void addCommand(Command command) {
        commands.add(command);
    }

    /**
     * Starts checking for args matches recursively<br>
     * TODO: Make this method a boolean to send a message if the command doesn't exist (magical check)
     * @param sender Sender of the command
     * @param args Arguments of the command
     */
    public void call(Sender sender, String[] args) {
        for (Command command : getCommands()) {
            if (command.getCommand().equals(args[0])) {
                if(!call(sender, Arrays.copyOfRange(args, 1, args.length), command)) {
                    command.execute(sender, Arrays.copyOfRange(args,1,args.length));
                }
                return;
            }
        }
        call(sender, new String[]{"help"});
    }

    /**
     * Checks if there are commands available on higher levels
     * @param sender Sender of the command
     * @param args Arguments given, including higher commands but not the sent one.
     * @param topCommand Higher command achieved
     * @return False if there are no commands higher. True if the last command has been executed.
     */
    public boolean call(Sender sender, String[] args, @NotNull Command topCommand) {
        for (Command command : topCommand.getSubcommands()) {
            if (command.getCommand().equals(args[0])) {
                if(!call(sender, Arrays.copyOfRange(args,1,args.length),command)) {
                    command.execute(sender,args);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the help line for the requested command
     * @param command Command from which to extract help
     * @return Help line for the requested command
     */
    public String getHelp(Command command) {
        StringBuilder help = new StringBuilder();
        help.append("/motdchanger ")
                .append(command.getCommand()).append(" ")
                .append(getParams(command)).append("- ")
                .append(command.getDescription());
        for (Command subcommand : command.getSubcommands()) {
            help.append("\n")
                    .append(getHelp(subcommand));
        }
        help.append("---");
        return String.valueOf(help);
    }

    /**
     * Gets the proper parameter containers.
     * @param command Command from which to extract parameters
     * @return Parameters between braces or smaller and bigger than symbols
     */
    public String getParams(Command command) {
        StringBuilder params = new StringBuilder();
        for (String param : command.getParams()) {
            String parameter;
            if (param.startsWith("%o")) parameter = "["+param.substring(1)+"]";
            else parameter = "<"+param+">";
            params.append(parameter).append(" ");
        }
        return String.valueOf(params);
    }

    /**
     * Gets all the help <br>TODO: Add a way to exclude, or only include, the specified command and its subcommands.
     * @return All commands help
     */
    public String getAllHelp() {
        StringBuilder help = new StringBuilder();
        for (Command command : getCommands()) {
            help.append(getHelp(command)).append("\n");
        }
        return String.valueOf(help);
    }

}
