package dev.tsetsi.motdchanger.bukkit;

public class CommandData {

    public CommandData(String command, String subcommand, String argument1, String argument2, String description) {
        this.command = command;
        this.subcommand = subcommand;
        this.argument1 = argument1;
        this.argument2 = argument2;
        this.description = description;
    }

    private String command;
    private String subcommand;
    private String argument1;
    private String argument2;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getSubcommand() {
        return subcommand;
    }

    public void setSubcommand(String subcommand) {
        this.subcommand = subcommand;
    }

    public String getArgument1() {
        return argument1;
    }

    public void setArgument1(String argument1) {
        this.argument1 = argument1;
    }

    public String getArgument2() {
        return argument2;
    }

    public void setArgument2(String argument2) {
        this.argument2 = argument2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

}
