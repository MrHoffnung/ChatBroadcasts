package de.sqlcxde.chatbroadcasts.scheduler;

import de.sqlcxde.chatbroadcasts.ChatBroadcasts;
import de.sqlcxde.chatbroadcasts.file.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class BroadcastScheduler {
    private int seconds;
    private boolean running;
    private int id;

    public BroadcastScheduler(int seconds) {
        this.seconds = seconds;
        this.running = false;
    }

    public void start() {
        run();
    }

    public void cancel() {
        if (running)
            Bukkit.getScheduler().cancelTask(id);
    }

    private void run() {
        this.running = true;
        id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ChatBroadcasts.getInstance(), new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int size = FileManager.getMessages().size();
                int number = random.nextInt(((size - 1)) + 1);
                Bukkit.broadcastMessage(FileManager.getMessages().get(number));
            }
        }, 0L, (seconds * 20L));
    }
}
