package de.sqlcxde.chatbroadcasts.file;

import de.sqlcxde.chatbroadcasts.ChatBroadcasts;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {
    private File file;
    private FileConfiguration cfg;
    private List<String> messages;


    public FileManager() {
        this.file = new File(ChatBroadcasts.getInstance().getDataFolder(), "config.yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);
        this.messages = Arrays.asList("§aThis is the first message!", "§bThis is the second message!");
    }

    public void createFile() throws IOException {
        if (!ChatBroadcasts.getInstance().getDataFolder().mkdir()) ChatBroadcasts.getInstance().getDataFolder().createNewFile();
        if (!file.exists()) {
            file.createNewFile();
            cfg.set("seconds", 120);
            cfg.set("messages", messages);
            cfg.save(file);
            ChatBroadcasts.getInstance().getLogger().info("The file config.yml has been created successfully!");
        }
    }

    public List<String> getMessages() {
        return cfg.getStringList("messages");
    }

    public Integer getSeconds() {
        return cfg.getInt("seconds");
    }
}
