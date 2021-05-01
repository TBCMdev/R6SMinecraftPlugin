package me.R6SMC.plugin.menu;

import me.R6SMC.plugin.DevConsole.DevConsole;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerMenus {

    private static final HashMap<Player,PlayerMenuUtility> PlayerMenuMap = new HashMap<>();

    public static PlayerMenuUtility GetPlayerMenuUtility(Player p){
        PlayerMenuUtility playerMenuUtility;
        if(PlayerMenuMap.containsKey(p)){
            DevConsole.SendDevMessage(p,"map of PlayerMenuUtility classes allready has your name.",DevConsole.TESTING);
            return PlayerMenuMap.get(p);
        }else{
            playerMenuUtility = new PlayerMenuUtility(p);
            DevConsole.SendDevMessage(p,"creating PlayerMenuUtility class.",DevConsole.TESTING);
            PlayerMenuMap.put(p,playerMenuUtility);
            return playerMenuUtility;
        }

    }
}
