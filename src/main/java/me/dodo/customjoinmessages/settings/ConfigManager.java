package me.dodo.customjoinmessages.settings;

import me.dodo.customjoinmessages.settings.classes.JoinMessages;
import me.dodo.customjoinmessages.settings.classes.QuitMessages;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class ConfigManager {
    private final File configFile;
    private final File configDirectory;
    private final JavaPlugin javaPlugin;
    private JoinMessages joinMessages;
    private QuitMessages quitMessages;

    public ConfigManager(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;

        this.configFile = new File(this.javaPlugin.getDataFolder(), "config.yml");
        this.configDirectory = new File(this.javaPlugin.getDataFolder().getPath());
    }

    public void loadConfig() {
        if (!this.configFile.exists())
            this.writeDefaultConfig();

        try {
            YamlConfiguration yamlConfiguration = new YamlConfiguration();
            yamlConfiguration.loadFromString(FileUtils.readFileToString(this.configFile, "UTF-8"));

            joinMessages = new JoinMessages(yamlConfiguration);
            quitMessages = new QuitMessages(yamlConfiguration);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void writeDefaultConfig() {
        this.javaPlugin.getLogger().info("Creating the default config.");
        InputStream inputStream = this.javaPlugin.getResource("config.yml");
        if (this.configDirectory.mkdirs()) {
            this.javaPlugin.getLogger().info("Created the plugin directory.");
        }
        try {
            if (this.configFile.createNewFile()) {
                this.javaPlugin.getLogger().info("Created the default config.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.configFile))) {
            assert inputStream != null;
            IOUtils.copy(inputStream, bufferedWriter, "UTF-8");
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
