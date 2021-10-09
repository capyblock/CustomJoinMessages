package me.dodo.customjoinmessages;

import me.dodo.customjoinmessages.events.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class CustomJoinMessages extends JavaPlugin {
    public static boolean PAPI = false;

    public static boolean joinDisable = false;
    public static boolean quitDisable = false;
    public static List<String> firstJoin = new ArrayList<>();
    public static List<String> joinMessages = new ArrayList<>();
    public static List<String> quitMessages = new ArrayList<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        joinDisable = getConfig().getBoolean("joinmessages.disable");
        quitDisable = getConfig().getBoolean("joinmessages.disable");
        if (!joinDisable) {
            firstJoin = getConfig().getStringList("joinmessages.firstjoin");
            joinMessages = getConfig().getStringList("joinmessages.messages");
        }
        if(!quitDisable) {
            quitMessages = getConfig().getStringList("quitmessages.messages");
        }
        if(!joinDisable || !quitDisable)
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
                PAPI = true;
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }
}
