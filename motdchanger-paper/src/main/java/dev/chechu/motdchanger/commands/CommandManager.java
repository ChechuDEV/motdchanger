package dev.chechu.motdchanger.commands;

import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashMap;

public class CommandManager {
    private final HashMap<String, Command> subcommands = new HashMap<>();

    public void addSubcommand(String subcommandName, Command subcommand) {
        subcommands.put(subcommandName, subcommand);
    }

    public HashMap<String, Command> getSubcommands() {
        return subcommands;
    }

    public boolean call(CommandSender sender, String[] args) {
        for (String commandName : getSubcommands().keySet()) {
            if (commandName.equals(args[0])) {
                if(!call(sender, Arrays.copyOfRange(args, 1, args.length))) {
                    getSubcommands().get(commandName).execute(sender, Arrays.copyOfRange(args,1,args.length));
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
        for (Command command : getSubcommands().values()) {
            help.append(getHelp(command)).append("\n");
        }
        return String.valueOf(help);
    }
}
