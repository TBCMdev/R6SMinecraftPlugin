package me.R6SMC.plugin.GameLogic;

import net.minecraft.server.v1_16_R3.Entity;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Spectate implements Listener{

    private static float delay = 0;
    public static boolean CheckToSetTarget(Player AlivePlayer){
        if(GameLogic.AlivePlayers.contains(AlivePlayer)){
            return true;
        }else if(GameLogic.DeadPlayers.contains(AlivePlayer)){
            return false;
        }
        return false;
    }

    public static void SetPlayerTarget(Player p, Player pS){
        //sets the players spectator target if they are dead until the game ends.
        EntityPlayer PlayerEntity = ((EntityPlayer) p).getBukkitEntity().getHandle();
        new BukkitRunnable() {
            @Override
            public void run() {
                if(GameLogic.GameStarted) {
                    if(CheckToSetTarget(p)) {
                        PlayerEntity.getSpecatorTarget();
                        PlayerEntity.setSpectatorTarget((Entity) p);
                    }else{
                        //p = new player
                        //also if all the players are dead you must cancel the operatotion
                        //GET RANDOM Teammate to set player to spectate
                    }
                }else{
                    cancel();
                }
            }
        }.runTaskTimer(GameLogic.mainThread,0,1);
    }
    public static void SetPlayerTarget(Player p,int team){
        //for setting most of the dead teammates spectator teammates
    }
    public static void RemovePlayerTarget(Player p){
        p.setSpectatorTarget(null);
    }

}
