package me.R6SMC.plugin.Listeners;

import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.OperatorClasses.OperatorAbilities.Grenade;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class GrenadeListener implements Listener {
    public boolean checkInsideRegion(Location l/*player*/,Grenade g){
        if(l.getX() > g.getLocation().getX() - g.getRange() &&
                l.getX() < g.getLocation().getX() + g.getRange() &&
                l.getY() > g.getLocation().getY() - g.getRange() &&
                l.getY() < g.getLocation().getY() + g.getRange() &&
                l.getZ() > g.getLocation().getZ() - g.getRange() &&
                l.getZ() < g.getLocation().getZ() + g.getRange()){

            return true;
        }
        return false;
    }
    @EventHandler
    public void playerMoveIntoGrenade(PlayerMoveEvent event){
        if(GameLogic.GameStarted){
            if(Grenade.checkForActiveGrenades()){
                List<Player> playersToDamage = new ArrayList<>();
                for(Grenade g : Grenade.currentGrenades.values()){
                    Location l = event.getPlayer().getLocation();
                    if(checkInsideRegion(l,g)){
                        if(!playersToDamage.contains(event.getPlayer())) {
                            event.getPlayer().sendMessage(ChatColor.RED + "you entered the grenade blast zone! leave"+ ChatColor.DARK_RED +" NOW!");
                            playersToDamage.add(event.getPlayer());
                            if (g.isDestroyed) {
                                event.getPlayer().setHealth(event.getPlayer().getHealth() - g.getDamage());
                                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 2, 1);
                                DevConsole.SendDevMessage(event.getPlayer(),"grenade has exploded.",DevConsole.TESTING);
                            }
                        }
                    }else{
                        playersToDamage.remove(event.getPlayer());
                        DevConsole.SendDevMessage(event.getPlayer(),"you left the grenade radius.",DevConsole.TESTING);

                    }


                }
            }
        }
    }
}
