package me.negi.armigerMC;

import me.negi.armigerMC.Weapons.Weapons.MortalSpear;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jspecify.annotations.NonNull;

public class CommandKit implements CommandExecutor {
    Plugin plugin;

    @Override
    public boolean onCommand(@NonNull CommandSender commandSender, @NonNull Command command, @NonNull String s, @NonNull String[] strings) {
        Player player = (Player) commandSender;
        MortalSpear spear = new MortalSpear(new NamespacedKey(plugin, "armigermc"));
        player.getInventory().addItem(spear.getWeaponItem());
        return true;
    }

    public CommandKit(Plugin plugina){
        plugin = plugina;
    }
}
