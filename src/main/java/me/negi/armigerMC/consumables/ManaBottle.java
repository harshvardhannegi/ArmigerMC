package me.negi.armigerMC.consumables;

import me.negi.armigerMC.util.DataKey;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class ManaBottle {
    private static ItemStack getSTACK(int manaAmount){
        ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("Mana Bottle");
        meta.setLore(List.of("A bottle of Mana",String.format("Consumes %d Mana", manaAmount)));
        meta.setEnchantmentGlintOverride(true);
        meta.getPersistentDataContainer().set(DataKey.getKey(), PersistentDataType.STRING, "armigermc" + manaAmount);
        item.setItemMeta(meta);
        return item;
    }


    public static ItemStack getManaBottle10(){
        return getSTACK(10);
    }

}
