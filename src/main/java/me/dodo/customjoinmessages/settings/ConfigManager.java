package me.dodo.customjoinmessages.settings;

import me.dodo.customjoinmessages.settings.classes.JoinMessages;
import me.dodo.customjoinmessages.settings.classes.QuitMessages;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class ConfigManager {
    private final File configFile;
    private final Path configDirectoryPath;
    private final JavaPlugin javaPlugin;
    private JoinMessages joinMessages;
    private QuitMessages quitMessages;

    public ConfigManager(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;

        this.configFile = new File(this.javaPlugin.getDataFolder(), "config.yml");
        this.configDirectoryPath = this.javaPlugin.getDataFolder().toPath();
    }

    public void loadConfig() {
        if (!this.configFile.exists())
            this.writeDefaultConfig();

        try {
            YamlConfiguration yamlConfiguration = new YamlConfiguration();
            yamlConfiguration.loadFromString(new String(Files.readAllBytes(this.configFile.toPath()), StandardCharsets.UTF_8));

            joinMessages = new JoinMessages(yamlConfiguration);
            quitMessages = new QuitMessages(yamlConfiguration);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void writeDefaultConfig() {
        this.javaPlugin.getLogger().info("Creating the default config.");
        try (InputStream inputStream = this.javaPlugin.getResource("config.yml")) {
            if (!Files.exists(configDirectoryPath)) {
                Files.createDirectories(configDirectoryPath);
                this.javaPlugin.getLogger().info("Created the plugin directory.");
            }
            if (!Files.exists(configFile.toPath())) {
                Files.createFile(configFile.toPath());
                this.javaPlugin.getLogger().info("Created the default config.");
            }
            if (inputStream != null) {
                Files.copy(inputStream, this.configFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JoinMessages getJoinMessages() {
        return joinMessages;
    }

    public QuitMessages getQuitMessages() {
        return quitMessages;
    }
}
