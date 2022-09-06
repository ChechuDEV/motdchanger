package dev.chechu.motdchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import org.bukkit.ChatColor;

import dev.chechu.motdchanger.exceptions.EmptyListException;
import net.kyori.adventure.platform.bukkit.BukkitComponentSerializer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.ParsingException;

public class MOTDManager {
    private final List<Component> mOTDList = new ArrayList<>();

    private final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final Consumer<MOTDManager> saveAction;

    private boolean rotation;

    public MOTDManager(Consumer<MOTDManager> saveAction) {
        this.saveAction = saveAction;
    }

    public void reload(List<String> mOTDStrings, boolean rotation) throws EmptyListException {
        mOTDList.clear();

        if (mOTDStrings.isEmpty()) {
            throw new EmptyListException();
        }

        for (String string : mOTDStrings) {
            mOTDList.add(convert(string));
        }

        this.rotation = rotation;
    }

    private String translateColorSymbols(String string) {
        return ChatColor.translateAlternateColorCodes('&', string); // TODO Change to another class
    }

    private Component deserialize(String content) {
        try {
            return miniMessage.deserialize(content);
        } catch (ParsingException e) {
            return BukkitComponentSerializer.legacy()
                    .deserialize(content.replace("%newline%", System.getProperty("line.separator"))); // TODO Change to
                                                                                                      // another class
        }
    }

    private Component convert(String content) {
        return deserialize(translateColorSymbols(content)); // TODO Change to another class
    }

    public String legacySerialize(Component component) {
        return BukkitComponentSerializer.legacy().serialize(component); // TODO Change to another class
    }

    public void addMOTD(String mOTD) {
        mOTDList.add(convert(mOTD));
        save();
    }

    public void removeMOTD(int index) {
        mOTDList.remove(index);
        save();
    }

    public void setMOTD(int index, String mOTD) {
        mOTDList.set(index, convert(mOTD));
    }

    private void save() {
        saveAction.accept(this);
    }

    public List<Component> getAllMOTD() {
        return mOTDList;
    }

    public List<String> serializeAllMOTD() {
        List<String> list = new ArrayList<>();
        for (Component component : getAllMOTD()) {
            list.add(miniMessage.serialize(component)); // TODO Change to another class
        }
        return list;
    }

    public boolean isRotation() {
        return rotation;
    }

    private final Random random = new Random();

    public Component getMOTD() {
        if (mOTDList.isEmpty()) {
            return convert("<aqua>Server is running smooth :)</aqua><newline><gold>Be happy!</gold>");
        }
        if (isRotation()) {
            return mOTDList.get(random.nextInt(mOTDList.size()));
        }
        return mOTDList.get(0);
    }
}
