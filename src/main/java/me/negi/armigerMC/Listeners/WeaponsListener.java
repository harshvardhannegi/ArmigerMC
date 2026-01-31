package me.negi.armigerMC.Listeners;

import me.negi.armigerMC.Weapons.WeaponManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;

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
        if (event.getDamager() instanceof Player && ((Player) event.getDamager()).getPlayer() !=null){
            wm.executeMeleeWeapon((((Player) event.getDamager()).getPlayer()), event.getEntity());
        }
    }

    @EventHandler
    public void onBowEvent(EntityShootBowEvent Event){
        if(Event.getEntity() instanceof Player && ((Player) Event.getEntity()).getPlayer() !=null) {

        }
    }


}
