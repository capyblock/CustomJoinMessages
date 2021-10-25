package me.dodo.customjoinmessages.events;

import me.dodo.customjoinmessages.modules.MessageModifier;
import me.dodo.customjoinmessages.settings.ConfigManager;
import me.dodo.customjoinmessages.settings.classes.QuitMessages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    private final QuitMessages config;

    private final String quitMessage;

    public PlayerQuit(ConfigManager configManager) {
        this.config = configManager.getQuitMessages();

        quitMessage = String.join("\n", config.getContext());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (!config.isEnabled()) {
            event.setQuitMessage(null);
            return;
        }

        event.setQuitMessage(MessageModifier.getText(event.getPlayer(), quitMessage));
    }
}
