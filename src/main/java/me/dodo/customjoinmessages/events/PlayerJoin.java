package me.dodo.customjoinmessages.events;

import me.clip.placeholderapi.PlaceholderAPI;
import me.dodo.customjoinmessages.CustomJoinMessages;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (CustomJoinMessages.joinDisable) {
            event.setJoinMessage(null);
            return;
        }

        if (!CustomJoinMessages.firstJoinDisable)
            if (!event.getPlayer().hasPlayedBefore()) {
                if (CustomJoinMessages.PAPI)
                    event.setJoinMessage(
                            PlaceholderAPI.setPlaceholders(event.getPlayer(),
                                    ChatColor.translateAlternateColorCodes('&',
                                            String.join("\n", CustomJoinMessages.firstJoin)))
                    );
                else
                    event.setJoinMessage(
                            ChatColor.translateAlternateColorCodes('&',
                                    String.join("\n", CustomJoinMessages.firstJoin))
                    );

                return;
            }


        if (!CustomJoinMessages.joinMessagesDisable)
            if (CustomJoinMessages.PAPI)
                event.setJoinMessage(
                        PlaceholderAPI.setPlaceholders(
                                event.getPlayer(),
                                ChatColor.translateAlternateColorCodes('&',
                                        String.join("\n", CustomJoinMessages.joinMessages)))
                );
            else
                event.setJoinMessage(
                        ChatColor.translateAlternateColorCodes('&',
                                String.join("\n", CustomJoinMessages.joinMessages))
                );
    }
}
