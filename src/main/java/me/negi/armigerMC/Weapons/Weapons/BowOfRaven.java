package me.negi.armigerMC.Weapons.Weapons;

import me.negi.armigerMC.Weapons.WeaponBow;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class BowOfRaven implements WeaponBow {

    NamespacedKey key;
    private final Plugin plugin;

    public BowOfRaven(NamespacedKey key, Plugin plugin){
        this.key = key;
        this.plugin = plugin;
    }

    @Override
    public void onExecution(Arrow arrow) {

        World world = arrow.getWorld();

        new BukkitRunnable() {

            double angle = 0;
            Location loc;

            @Override
            public void run() {

                if (!arrow.isValid() || arrow.isDead() || arrow.isOnGround()) {
                    world.strikeLightning(loc);
                    cancel();
                    return;
                }

                loc = arrow.getLocation();
                World world = loc.getWorld();

                Vector dir = arrow.getVelocity().normalize();
                Vector perp1 = new Vector(-dir.getZ(), 0, dir.getX()).normalize();
                Vector perp2 = dir.clone().crossProduct(perp1).normalize();

                double radius = 0.4;

                // spawn multiple points per tick
                for (int i = 0; i < 6; i++) {

                    double currentAngle = angle + (i * 0.4);

                    double x = Math.cos(currentAngle) * radius;
                    double y = Math.sin(currentAngle) * radius;

                    Vector offset = perp1.clone().multiply(x)
                            .add(perp2.clone().multiply(y));

                    world.spawnParticle(
                            Particle.FLAME,
                            loc.clone().add(offset),
                            1,
                            0, 0, 0, 0
                    );
                }

                angle += 0.3;
            }

        }.runTaskTimer(plugin, 0L, 1L);

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
