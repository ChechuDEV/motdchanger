package dev.chechu.motdchanger.commands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandManager {
    private final List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void call(CommandSender sender, String[] args) {
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

    public boolean call(CommandSender sender, String[] args, @NotNull Command topCommand) {
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

    public String getAllHelp() {
        StringBuilder help = new StringBuilder();
        for (Command command : getCommands()) {
            help.append(getHelp(command)).append("\n");
        }
        return String.valueOf(help);
    }
}
