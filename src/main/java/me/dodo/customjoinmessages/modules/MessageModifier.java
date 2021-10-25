package me.dodo.customjoinmessages.modules;

import me.clip.placeholderapi.PlaceholderAPI;
import me.dodo.customjoinmessages.CustomJoinMessages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class MessageModifier {
    public static String getText(Player player, String text) {
        String tempText = ChatColor.translateAlternateColorCodes('&', text);
        if (CustomJoinMessages.isPapiEnabled)
            tempText = PlaceholderAPI.setPlaceholders(player, text);
        return tempText;
    }
}
