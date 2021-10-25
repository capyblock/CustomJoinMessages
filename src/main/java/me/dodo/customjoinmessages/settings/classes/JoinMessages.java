package me.dodo.customjoinmessages.settings.classes;

import me.dodo.customjoinmessages.settings.interfaces.joinmessages.FirstJoin;
import me.dodo.customjoinmessages.settings.interfaces.joinmessages.Main;
import me.dodo.customjoinmessages.settings.interfaces.joinmessages.Messages;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class JoinMessages implements Main, FirstJoin, Messages {
    private final ConfigurationSection mainSection;
    private final ConfigurationSection firstJoinSection;
    private final ConfigurationSection messagesSection;

    public JoinMessages(YamlConfiguration yamlConfiguration) {
        mainSection = yamlConfiguration.getConfigurationSection("joinmessages");
        assert mainSection != null;
        firstJoinSection = mainSection.getConfigurationSection("firstjoin");
        messagesSection = mainSection.getConfigurationSection("messages");
    }

    @Override
    public boolean isFirstJoinEnabled() {
        return firstJoinSection.getBoolean("enabled");
    }

    @Override
    public List<String> getFirstJoinContext() {
        return firstJoinSection.getStringList("context");
    }

    @Override
    public boolean isEnabled() {
        return mainSection.getBoolean("enabled");
    }

    @Override
    public boolean isMessagesEnabled() {
        return messagesSection.getBoolean("enabled");
    }

    @Override
    public List<String> getMessagesContext() {
        return messagesSection.getStringList("context");
    }
}
