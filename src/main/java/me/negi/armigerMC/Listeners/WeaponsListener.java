package me.negi.armigerMC.Listeners;

import me.negi.armigerMC.Weapons.WeaponManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Objects;

public class WeaponsListener implements Listener {

    WeaponManager wm;

    public WeaponsListener(WeaponManager manager)
    {
        wm = manager;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event)
    {
        if (event.getDamager() instanceof Player){
            wm.executeMeleeWeapon(Objects.requireNonNull(((Player) event.getDamager()).getPlayer()), event.getEntity());
        }
    }


}
