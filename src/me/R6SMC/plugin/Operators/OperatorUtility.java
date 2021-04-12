package me.R6SMC.plugin.Operators;

import me.R6SMC.plugin.PlayerClass;
import org.bukkit.entity.Player;

public class OperatorUtility {
    public OperatorUtility(Player owner, PlayerClass pc){
        this.owner = owner;
        this.pc = pc;
    }
    private Player owner;
    private PlayerClass pc;
    public Player getOwner(){
        return owner;
    }
    public PlayerClass getOwnerClass(){
        return this.pc;
    }
    public void setOwner(Player owner){
        this.owner = owner;
    }
}
