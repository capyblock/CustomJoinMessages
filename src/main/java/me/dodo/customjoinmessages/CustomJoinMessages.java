package me.dodo.customjoinmessages;

import me.dodo.customjoinmessages.events.PlayerJoin;
import me.dodo.customjoinmessages.events.PlayerQuit;
import me.dodo.customjoinmessages.settings.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomJoinMessages extends JavaPlugin {
    public static boolean isPapiEnabled = false;

    @Override
    public void onEnable() {
        ConfigManager configManager = new ConfigManager(this);
        configManager.loadConfig();
        if (!configManager.getJoinMessages().isEnabled() && !configManager.getQuitMessages().isEnabled())
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                isPapiEnabled = true;
                getLogger().info("PlaceHolderAPI found!");
            }
        getServer().getPluginManager().registerEvents(new PlayerJoin(configManager), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(configManager), this);
    }
}
