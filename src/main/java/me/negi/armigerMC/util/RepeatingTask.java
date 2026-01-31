package me.negi.armigerMC.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public abstract class RepeatingTask implements Runnable {
    private final int taskId;

    public RepeatingTask(Plugin plugin, int seconds) {
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0L, seconds*20L);
    }

    public void cancel() {
        Bukkit.getScheduler().cancelTask(taskId);
    }
}
