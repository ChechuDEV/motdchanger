package dev.tsetsi.motdchanger.bukkit;

public class CommandData {

    public CommandData(String command, Boolean rotationCommand, String argument1, String argument2, String description) {
        this.command = command;
        this.rotationCommand = rotationCommand;
        this.argument1 = argument1;
        this.argument2 = argument2;
        this.description = description;
    }

    public CommandData(String command, Boolean rotationCommand, String description) {
        this.command = command;
        this.rotationCommand = rotationCommand;
        this.description = description;
    }

    public CommandData(String command, Boolean rotationCommand, String argument1, String description) {
        this.command = command;
        this.rotationCommand = rotationCommand;
        this.argument1 = argument1;
        this.description = description;
    }

    private final String command;
    private final Boolean rotationCommand;
    private String argument1 = "";
    private String argument2 = "";

    public Boolean getRotationCommand() {
        return rotationCommand;
    }


    public String getCommand() {
        return command;
    }

    public String getArgument1() {
        return argument1;
    }

    public String getArgument2() {
        return argument2;
    }

    public String getDescription() {
        return description;
    }

    private String description;

}
