package me.R6SMC.plugin.GameLogic;

import me.R6SMC.plugin.DevConsole.DevConsole;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    @EventHandler
    public void OnGameDeath(PlayerDeathEvent e){
        if(GameLogic.GameStarted){
            Player p = e.getEntity();
            if(GameLogic.AlivePlayers.contains(p)){
                if(!GameLogic.DeadPlayers.contains(p)) {
                    GameLogic.AlivePlayers.remove(p);
                }else{
                    DevConsole.SendDevMessage(p,"Could Not Remove You From the AlivePlayers List",DevConsole.TESTING);
                }

            }
        }
    }
}
