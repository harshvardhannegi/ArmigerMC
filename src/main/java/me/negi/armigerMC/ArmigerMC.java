package me.negi.armigerMC;

import me.negi.armigerMC.Listeners.WeaponsListener;
import me.negi.armigerMC.Mana.ManaManager;
import me.negi.armigerMC.Weapons.WeaponManager;
import me.negi.armigerMC.Weapons.Weapons.MortalSpear;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ArmigerMC extends JavaPlugin {

    WeaponManager WM = new WeaponManager();
    ManaManager MM = new ManaManager();

    private static ArmigerMC instance;

    public static ArmigerMC getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        try {
            MM.loadData(this);
            Logger.getLogger(this.getName()).log(Level.INFO, "Successfully loaded Mana to Cache!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MM.StartManaCycle(this);
        WM.setManaManager(MM);
        WM.setupNameSpaceKey(this);
        Objects.requireNonNull(this.getCommand("kit")).setExecutor(new CommandKit(this));
        getServer().getPluginManager().registerEvents(new WeaponsListener(WM), this);

    }

    @Override
    public void onDisable() {
        try {
            MM.saveData(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
