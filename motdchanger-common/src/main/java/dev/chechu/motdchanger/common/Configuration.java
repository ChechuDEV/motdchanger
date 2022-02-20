package dev.chechu.motdchanger.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.logging.Logger;

public abstract class Configuration {
    private MotDManager motDManager;

    @Getter private boolean rotationEnabled = false;
    @Getter private boolean hidePlayersEnabled = false;
    @Getter private boolean checkUpdatesEnabled = false;
    @Getter private boolean autoUpdateEnabled = false;
    @Getter private boolean metricsEnabled = false;

    public abstract void createFolder();
    public abstract void createConfigFile();
    public abstract void fixConfig();

    public abstract void reloadConfig();
}
