package de.sqlcxde.chatbroadcasts;

import de.sqlcxde.chatbroadcasts.file.FileManager;
import de.sqlcxde.chatbroadcasts.scheduler.BroadcastScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class ChatBroadcasts extends JavaPlugin {
    private static ChatBroadcasts instance;
    private FileManager manager;

    @Override
    public void onEnable() {
        instance = this;
        manager = new FileManager();
        try {
            manager.createFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BroadcastScheduler task = new BroadcastScheduler(manager.getSeconds());
        task.start();
        getLogger().info("The scheduler has been started successfully!");
    }

    @Override
    public void onDisable() {
        BroadcastScheduler task = new BroadcastScheduler(manager.getSeconds());
        task.cancel();
    }

    public static ChatBroadcasts getInstance() {
        return instance;
    }
}
