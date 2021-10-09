package me.dodo.customjoinmessages.events;

import me.clip.placeholderapi.PlaceholderAPI;
import me.dodo.customjoinmessages.CustomJoinMessages;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if(CustomJoinMessages.quitDisable){
            event.setQuitMessage(null);
            return;
        }

        if(CustomJoinMessages.PAPI)
            event.setQuitMessage(PlaceholderAPI.setPlaceholders(event.getPlayer(), ChatColor.translateAlternateColorCodes('&', String.join("\n", CustomJoinMessages.quitMessages))));
        else
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', String.join("\n", CustomJoinMessages.quitMessages)));
    }
}
