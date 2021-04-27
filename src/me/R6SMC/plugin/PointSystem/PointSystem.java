package me.R6SMC.plugin.PointSystem;

import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.PlayerLogic.PlayerClass;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PointSystem {
    public static boolean SetPoints(PlayerClass p, int Points){
        try{
            p.SetPoints(Points);

            return true;
        }catch (Exception e){
            Bukkit.getLogger().info(e.toString());
        }
        return false;
    }
    public static void game_loseSound(){
        for(Player p : GameChat.GetAllPlayers()){
            p.playSound(p.getLocation(),Sound.ENTITY_CAT_DEATH,1,1);
        }
    }
    public static void round_Lose(){
        for(Player p : GameChat.GetAllPlayers()){
            p.playSound(p.getLocation(),Sound.ENTITY_ELDER_GUARDIAN_CURSE,1,1);
        }
    }
    public static void round_Win(){

    }
    public static void killSound(Player p){
        p.playSound(p.getLocation(), Sound.BLOCK_SAND_PLACE,1,1);
    }
    public static void team_KillSound(Player p){
        p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT,1,1);
    }
    public static void game_endSound(){

    }


}
