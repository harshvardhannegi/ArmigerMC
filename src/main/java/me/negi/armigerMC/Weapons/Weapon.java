package me.negi.armigerMC.Weapons;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Weapon {
     void onExecution(Player player, Entity entity);
     ItemStack getWeaponItem();
     int getMANACost();
}
