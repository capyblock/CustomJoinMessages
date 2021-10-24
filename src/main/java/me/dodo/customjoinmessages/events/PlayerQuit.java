package me.dodo.customjoinmessages.events;

import me.clip.placeholderapi.PlaceholderAPI;
import me.dodo.customjoinmessages.CustomJoinMessages;
import me.dodo.customjoinmessages.settings.classes.QuitMessages;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    private final QuitMessages config;

    public PlayerQuit() {
        config = CustomJoinMessages.getConfigManager().getQuitMessages();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (config.isDisabled()) {
            event.setQuitMessage(null);
            return;
        }

        if (CustomJoinMessages.PAPI)
            event.setQuitMessage(
                    PlaceholderAPI.setPlaceholders(
                            event.getPlayer(),
                            ChatColor.translateAlternateColorCodes('&',
                                    String.join("\n", config.getContext())))
            );
        else
            event.setQuitMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            String.join("\n", config.getContext()))
            );
    }
}
