package dev.chechu.motdchanger.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class Sender{
    @Getter private boolean consoleSender;
    @Getter private boolean commandBlockSender;
    @Getter private boolean playerSender;
    @Getter private Object sender;

    /**
     * Sends message to the Sender
     * @param message
     */
    public abstract void sendMessage(String message);

    /**
     * Sends actionBar to the Sender
     * @param actionBar
     */
    public abstract void sendActionBar(String actionBar);

    /**
     * Sends a title to the Sender
     * @param title
     * @param subtitle
     */
    public abstract void sendTitle(String title, String subtitle);
}
