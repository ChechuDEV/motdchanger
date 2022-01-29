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

    public String getHelp(Command subcommand) {
        StringBuilder subcommands = new StringBuilder();
        for (String subcommandSubcommand : subcommand.getSubcommands()) {
            subcommands.append(subcommandSubcommand).append(" ");
        }
        StringBuilder params = new StringBuilder();
        for (String subcommandParam : subcommand.getParams()) {
            String param;
            if (subcommandParam.startsWith("%o")) param = "["+subcommandParam.substring(1)+"]";
            else param = "<"+subcommandParam+">";
            params.append(param).append(" ");
        }
        return "/motdchanger " + subcommand.command + " " + subcommands + params + "- " + subcommand.getDescription();
    }

    public String getAllHelp() {
        StringBuilder help = new StringBuilder();
        for (Command subcommand : getSubcommands().values()) {
            help.append(getHelp(subcommand)).append("\n");
        }
        return String.valueOf(help);
    }

    public void call(String command, CommandSender sender, String[] args) {
        for (String subcommandName : getSubcommands().keySet()) {
            if (subcommandName.equals(args[0])) {
                getSubcommands().get(subcommandName).execute(sender, Arrays.copyOfRange(args, 1, args.length));
                break;
            }
            getSubcommands().get("help").execute(sender, new String[0]);
        }
    }
}
