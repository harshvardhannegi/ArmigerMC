package me.negi.armigerMC.Mana;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

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

    public void setMana(UUID uuid, int amt)
    {
        ManaMap.get(uuid).setMANA(getMana(uuid) - amt);
    }

    public void StartManaCycle(Plugin plugin) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            ManaMap.forEach((uuid, mana) -> {
                if (mana.getMANA() < mana.getMAX_MANA()) {
                    mana.setMANA(mana.getMANA() + 1);
                }

                Player player = Bukkit.getPlayer(uuid);
                if (player != null) {
                    player.sendMessage("Your current Mana is: " + mana.getMANA());
                }
            });
        }, 0L, 20L);
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
