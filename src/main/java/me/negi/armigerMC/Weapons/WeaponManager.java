package me.negi.armigerMC.Weapons;

import me.negi.armigerMC.Mana.ManaManager;
import me.negi.armigerMC.Weapons.Weapons.DarkLance;
import me.negi.armigerMC.Weapons.Weapons.MortalSpear;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WeaponManager {

    ManaManager mm;
    NamespacedKey key;
    private Map<String, Weapon> MELEEWEAPONLIST;
    private Map<String, Weapon> MAGICWEAPONLIST;

    public void executeMeleeWeapon(Player player, Entity entity)
    {
        String WEAPON_NAME = Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).getPersistentDataContainer().get(key, PersistentDataType.STRING);
        MELEEWEAPONLIST.forEach((N,W) -> {
            if(Objects.equals(WEAPON_NAME, N)){
                if(mm.getMana(player.getUniqueId()) < W.getMANACost())
                {
                    player.getWorld().spawnParticle(
                            Particle.DUST,
                            player.getLocation().add(0, 2, 0),
                            50
                    );
                    player.getWorld().spawnParticle(
                            Particle.DUST,
                            player.getLocation().add(1, 2, 0),
                            50
                    );
                }
                else
                {
                    mm.setMana(player.getUniqueId(), W.getMANACost());
                    W.onExecution(player, entity);
                }
            }
        });
    }

    public void setManaManager(ManaManager manager)
    {
        mm = manager;
    }

    public void setupNameSpaceKey(Plugin plugin)
    {
        key = new NamespacedKey(plugin, "armigermc");
        MELEEWEAPONLIST = Map.of("mortalspear", new MortalSpear(key));
        MAGICWEAPONLIST = Map.of("darklance", new DarkLance(key));
    }

}
