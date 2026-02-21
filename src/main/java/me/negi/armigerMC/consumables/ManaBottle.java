package me.negi.armigerMC.consumables;

import me.negi.armigerMC.util.DataKey;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class ManaBottle {
    private static ItemStack STACK;

    static {
        STACK = new ItemStack(Material.EXPERIENCE_BOTTLE);
    }


    public static ItemStack getManaBottle10(){
        ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("Mana Bottle");
        meta.setLore(List.of("A bottle of Mana","Provides 10 Mana"));
        meta.setEnchantmentGlintOverride(true);
        meta.getPersistentDataContainer().set(DataKey.getKey(), PersistentDataType.STRING, "armigermc:10");
        item.setItemMeta(meta);
        return item;
    }

}
