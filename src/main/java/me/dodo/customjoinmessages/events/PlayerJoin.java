package me.dodo.customjoinmessages.events;

import me.dodo.customjoinmessages.modules.MessageModifier;
import me.dodo.customjoinmessages.settings.ConfigManager;
import me.dodo.customjoinmessages.settings.classes.JoinMessages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    private final JoinMessages config;
    private final String firstJoinMessage;
    private final String normalMessage;

    public PlayerJoin(ConfigManager configManager) {
        this.config = configManager.getJoinMessages();

        firstJoinMessage = String.join("\n", config.getFirstJoinContext());
        normalMessage = String.join("\n", config.getMessagesContext());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!config.isEnabled()) {
            event.setJoinMessage(null);
            return;
        }

        if (config.isFirstJoinEnabled()) {
            if (!event.getPlayer().hasPlayedBefore()) {
                event.setJoinMessage(MessageModifier.getText(event.getPlayer(), firstJoinMessage));
                return;
            }
        }


        if (config.isMessagesEnabled())
            event.setJoinMessage(MessageModifier.getText(event.getPlayer(), normalMessage));
        else
            event.setJoinMessage(null);
    }
}
