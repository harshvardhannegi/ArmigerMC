package me.negi.armigerMC.Weapons.Weapons;

import me.negi.armigerMC.Weapons.Weapon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BowOfRaven implements Weapon {
    @Override
    public void onExecution(Player player, Entity entity) {

    }

    @Override
    public ItemStack getWeaponItem() {
        return null;
    }

    @Override
    public int getMANACost() {
        return 0;
    }
}
