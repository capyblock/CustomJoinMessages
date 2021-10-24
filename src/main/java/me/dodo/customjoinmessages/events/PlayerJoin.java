package me.dodo.customjoinmessages.events;

import me.clip.placeholderapi.PlaceholderAPI;
import me.dodo.customjoinmessages.CustomJoinMessages;
import me.dodo.customjoinmessages.settings.classes.JoinMessages;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    private final JoinMessages config;
    public PlayerJoin(){
        config = CustomJoinMessages.getConfigManager().getJoinMessages();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (config.isMessagesDisabled() || config.isDisabled()) {
            event.setJoinMessage(null);
            return;
        }

        if (!config.isFirstJoinDisabled())
            if (!event.getPlayer().hasPlayedBefore()) {
                if (CustomJoinMessages.PAPI)
                    event.setJoinMessage(
                            PlaceholderAPI.setPlaceholders(event.getPlayer(),
                                    ChatColor.translateAlternateColorCodes('&',
                                            String.join("\n", config.getFirstJoinContext())))
                    );
                else
                    event.setJoinMessage(
                            ChatColor.translateAlternateColorCodes('&',
                                    String.join("\n", config.getFirstJoinContext()))
                    );

                return;
            }


        if (!config.isMessagesDisabled())
            if (CustomJoinMessages.PAPI)
                event.setJoinMessage(
                        PlaceholderAPI.setPlaceholders(
                                event.getPlayer(),
                                ChatColor.translateAlternateColorCodes('&',
                                        String.join("\n", config.getMessagesContext())))
                );
            else
                event.setJoinMessage(
                        ChatColor.translateAlternateColorCodes('&',
                                String.join("\n", config.getMessagesContext()))
                );
    }
}
