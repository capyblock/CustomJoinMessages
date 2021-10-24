package me.dodo.customjoinmessages;

import me.dodo.customjoinmessages.events.PlayerJoin;
import me.dodo.customjoinmessages.events.PlayerQuit;
import me.dodo.customjoinmessages.settings.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomJoinMessages extends JavaPlugin {
    public static boolean PAPI = false;
    private static ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        configManager.loadConfig();
        if (configManager.getJoinMessages().isDisabled() && configManager.getQuitMessages().isDisabled())
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
                PAPI = true;
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }
}
