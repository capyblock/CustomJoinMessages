package me.dodo.customjoinmessages;

import me.dodo.customjoinmessages.events.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class CustomJoinMessages extends JavaPlugin {
    public static boolean PAPI = false;

    public static boolean joinDisable = false;
    public static boolean joinMessagesDisable = false;
    public static boolean quitDisable = false;
    public static boolean firstJoinDisable = false;
    public static List<String> firstJoin = new ArrayList<>();
    public static List<String> joinMessages = new ArrayList<>();
    public static List<String> quitMessages = new ArrayList<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        joinDisable = getConfig().getBoolean("joinmessages.disable");
        joinMessagesDisable = getConfig().getBoolean("joinmessages.messages.disable");
        firstJoinDisable = getConfig().getBoolean("joinmessages.firstjoin.disable");
        quitDisable = getConfig().getBoolean("quitmessages.disable");
        if (!joinDisable) {
            if (!firstJoinDisable)
                firstJoin = getConfig().getStringList("joinmessages.firstjoin.context");
            if (!joinMessagesDisable)
                joinMessages = getConfig().getStringList("joinmessages.messages.context");
        }
        if (!quitDisable) {
            quitMessages = getConfig().getStringList("quitmessages.messages");
        }
        if (!joinDisable || !quitDisable)
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
                PAPI = true;
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }
}
