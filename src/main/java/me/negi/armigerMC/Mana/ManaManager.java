package me.negi.armigerMC.Mana;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.negi.armigerMC.util.RepeatingTask;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ManaManager {

    private Map<UUID, Mana> ManaMap = new HashMap<>();
    private final Gson gson = new Gson();

    private Mana defaultMana()
    {
        Mana mana = new Mana();
        mana.setMANA(20);
        mana.setMANA_STRENGTH(1);
        mana.setMAX_MANA(20);
        return mana;
    }

    String getMana(int MaxMana, int CurrentMana)
    {
        if (MaxMana <= 0) return "□□□□□□□□□□ " + CurrentMana;
        int filled = (int) Math.round((CurrentMana / (double) MaxMana) * 10);
        filled = Math.max(0, Math.min(10, filled));
        return "■".repeat(filled) + "□".repeat(10 - filled).concat(" " + CurrentMana);
    }

    public void setMana(UUID uuid, int amt)
    {
        ManaMap.get(uuid).setMANA(getMana(uuid) - amt);
    }

    public void StartManaCycle(Plugin plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Mana mana = ManaMap.get(player.getUniqueId());
                if (mana == null) continue;
                int current = mana.getMANA();
                int max = mana.getMAX_MANA();
                if (mana.getMANA() < max) {
                    mana.setMANA(Math.min(current + mana.getMANA_STRENGTH(), max));
                }
            }
        }, 0L, 20L);

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Mana mana = ManaMap.get(player.getUniqueId());
                if (mana == null) continue;
                int current = mana.getMANA();
                int max = mana.getMAX_MANA();
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(getMana(max, current), ChatColor.LIGHT_PURPLE));
            }
        }, 0L, 1L);
    }

    public int getMana(UUID uuid)
    {
        return ManaMap.computeIfAbsent(uuid,mana -> defaultMana()).getMANA();
    }

    public void saveData(Plugin plugin) throws IOException {
        String jsonData = gson.toJson(ManaMap);
        if(!Files.exists(plugin.getDataFolder().toPath())){
            Files.createDirectory(plugin.getDataFolder().toPath());
        }
        Files.writeString(Path.of(plugin.getDataFolder() + "/" + "manaList.json"), jsonData);
    }

    public void loadData(Plugin plugin) throws IOException {
        Path path = Path.of(plugin.getDataFolder() + "/" + "manaList.json");

        if (Files.exists(path)) {
            String jsonData = Files.readString(path);

            Type type = new TypeToken<Map<UUID, Mana>>() {}.getType();
            ManaMap = gson.fromJson(jsonData, type);
        }
    }

}
