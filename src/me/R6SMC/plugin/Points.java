package me.R6SMC.plugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Points {
    public static boolean SetPoints(PlayerClass p, int Points){
        try{
            p.SetPoints(Points);
            return true;
        }catch (Exception e){
            Bukkit.getLogger().info(e.toString());
        }
        return false;
    }
}
