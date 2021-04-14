package me.R6SMC.plugin.Sounds;

import me.R6SMC.plugin.GameLogic.GameLogic;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Sounds {


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
}
