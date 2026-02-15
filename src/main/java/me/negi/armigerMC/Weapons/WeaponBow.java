package me.negi.armigerMC.Weapons;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

public interface WeaponBow {
     void onExecution(Arrow arrow);
     ItemStack getWeaponItem();
     int getMANACost();
}
