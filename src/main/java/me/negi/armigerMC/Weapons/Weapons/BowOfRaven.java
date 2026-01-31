package me.negi.armigerMC.Weapons.Weapons;

import me.negi.armigerMC.Weapons.Weapon;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class BowOfRaven implements Weapon {

    NamespacedKey key;

    public BowOfRaven(NamespacedKey key){
        this.key = key;
    }

    @Override
    public void onExecution(Player player, Entity entity) {

    }

    @Override
    public ItemStack getWeaponItem() {
        ItemStack stack = new ItemStack(Material.BOW);
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setDisplayName("Bow Of Raven");
        meta.setLore(Arrays.asList("A Divine bow, once veiled by a Rogue.", "Uses 10 MANA"));
        meta.addEnchant(Enchantment.UNBREAKING, 10, true);
        meta.addEnchant(Enchantment.POWER, 12, true);
        meta.addEnchant(Enchantment.FLAME, 1, true);
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "bowofraven");
        stack.setItemMeta(meta);
        return stack;
    }

    @Override
    public int getMANACost() {
        return 10;
    }
}
