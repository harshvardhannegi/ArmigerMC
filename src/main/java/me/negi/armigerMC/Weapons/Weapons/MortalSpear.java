package me.negi.armigerMC.Weapons.Weapons;

import me.negi.armigerMC.Weapons.Weapon;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MortalSpear implements Weapon {

    NamespacedKey key;

    public MortalSpear(NamespacedKey Key)
    {
        key = Key;
    }

    @Override
    public void onExecution(Player player, Entity entity) {
        PotionEffect effect = new PotionEffect(PotionEffectType.DARKNESS, 10,1);
        PotionEffect effect1 = new PotionEffect(PotionEffectType.POISON, 10,1);
        effect.apply((LivingEntity) entity);
        effect1.apply((LivingEntity) entity);
    }

    @Override
    public ItemStack getWeaponItem() {
        ItemStack stack = new ItemStack(Material.NETHERITE_SPEAR);
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setDisplayName("Mortal Spear");
        meta.setLore(Arrays.asList("A Mortal Spear, Capable of slaying any being.", "Uses 2 MANA"));
        meta.addEnchant(Enchantment.UNBREAKING, 10, true);
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "mortalspear");
        stack.setItemMeta(meta);
        return stack;
    }

    @Override
    public int getMANACost() {
        return 5;
    }
}
