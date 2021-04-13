package me.R6SMC.plugin.menu;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerMenus {

    private static final HashMap<Player,PlayerMenuUtility> PlayerMenuMap = new HashMap<>();

    public static PlayerMenuUtility GetPlayerMenuUtility(Player p){
        PlayerMenuUtility playerMenuUtility;
        if(PlayerMenuMap.containsKey(p)){
            return PlayerMenuMap.get(p);
        }else{
            playerMenuUtility = new PlayerMenuUtility(p);
            PlayerMenuMap.put(p,playerMenuUtility);
            return playerMenuUtility;
        }

    }
}
