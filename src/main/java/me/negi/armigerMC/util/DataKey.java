package me.negi.armigerMC.util;

import me.negi.armigerMC.ArmigerMC;
import org.bukkit.NamespacedKey;

public class DataKey {
    private static final NamespacedKey key = new NamespacedKey(ArmigerMC.getInstance(), "armigermc");

    public static NamespacedKey getKey(){
        return key;
    }
}
