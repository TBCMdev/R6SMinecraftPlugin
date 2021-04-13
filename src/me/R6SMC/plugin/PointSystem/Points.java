package me.R6SMC.plugin.PointSystem;

import me.R6SMC.plugin.PlayerLogic.PlayerClass;
import org.bukkit.Bukkit;

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
