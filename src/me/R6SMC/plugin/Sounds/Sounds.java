package me.R6SMC.plugin.Sounds;

import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.PlayerLogic.PlayerClass;
import me.R6SMC.plugin.PointSystem.PointSystem;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Sounds {

    public static HashMap<Integer,Sound> Playable_Sounds_Int = new HashMap<Integer,Sound>(){{
        for(PlayerClass.PointType p : PlayerClass.getPointTypeValues()){
            put(p.ordinal(),Sounds.getSound(p.name()));
        }
    }};
    public static HashMap<String,Sound> Playable_Sounds_Str = new HashMap<String,Sound>(){{
        for(PlayerClass.PointType p : PlayerClass.getPointTypeValues()){
            put(p.name().toLowerCase(),Sounds.getSound(p.name()));
        }
    }};
//https://drive.google.com/drive/folders/1bpqp9UG7nNpT7NZk-fu9y0gBjJanz-TQ
    public static void PlaySound(Sound s, Player p){
        p.playSound(p.getLocation(),s,1,1);
    }
    public static void PlaySound(Sound s, Player p,float volume1,float volume2){
        p.playSound(p.getLocation(),s,volume1,volume2);
    }
    public static void PlaySoundToAll(Sound s){
        for(Player p : GameLogic.RedTeam){
            p.playSound(p.getLocation(),s,1,1);
        }
        for(Player p : GameLogic.BlueTeam){
            p.playSound(p.getLocation(),s,1,1);
        }
    }
    public static void PlaySoundToRedTeam(Sound s){
        for(Player p : GameLogic.RedTeam){
            p.playSound(p.getLocation(),s,1,1);
        }
    }
    public static void PlaySoundToBlueTeam(Sound s){
        for(Player p : GameLogic.BlueTeam){
            p.playSound(p.getLocation(),s,1,1);
        }
    }

    public static Sound getSound(String name) {
        try{
            switch (name.toLowerCase()){
                case "kill":
                    return Sound.BLOCK_SAND_PLACE;
                case "team_kill":
                    return Sound.ENTITY_CAT_HURT;
                case "round_lose":
                    return Sound.ENTITY_ZOMBIE_DEATH;
                case "round_win":
                    return Sound.ENTITY_ZOMBIE_DEATH;
                case "game_win":
                    return Sound.ENTITY_ZOMBIE_DEATH;
                case "game_lose":
                    return Sound.ENTITY_ZOMBIE_DEATH;
            }
        }catch (Exception e){

        }
        return null;
    }

    public static Sound getSound(int i) {
        return null;
    }
}
