package me.dodo.customjoinmessages.settings.classes;

import me.dodo.customjoinmessages.settings.interfaces.quitmessages.Main;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class QuitMessages implements Main {
    private final ConfigurationSection configurationSection;

    public QuitMessages(YamlConfiguration yamlConfiguration) {
        configurationSection = yamlConfiguration.getConfigurationSection("quitmesssages");
    }

    @Override
    public boolean isDisabled() {
        return configurationSection.getBoolean("disable");
    }

    @Override
    public List<String> getContext() {
        return configurationSection.getStringList("messages");
    }
}
