package dev.chechu.motdchanger;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "motdchanger-sponge",
        name = "Motdchanger",
        version = "1.0",
        description = "A plugin that allows you to change server's MOTD in-game and set rotating MOTDs with even hex-colors!",
        authors = {
                "Chechu"
        }
)
public class sponge {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
    }
}
