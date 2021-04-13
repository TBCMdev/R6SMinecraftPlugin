package me.R6SMC.plugin.menu;

import org.bukkit.entity.Player;

public class PlayerMenuUtility {
    public PlayerMenuUtility(Player owner){
        this.owner = owner;
    }
    private Player owner;
    public Player getOwner(){
        return owner;
    }
    public void setOwner(Player owner){
        this.owner = owner;
    }
}
