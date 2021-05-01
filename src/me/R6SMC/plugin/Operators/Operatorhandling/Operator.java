package me.R6SMC.plugin.Operators.Operatorhandling;

import me.R6SMC.plugin.PlayerLogic.PlayerClass;
import org.bukkit.entity.Player;

import java.util.HashMap;

public abstract class Operator {

    public static HashMap<String,Player> OperatorStorage = new HashMap<>();
    protected int AbilityCount;
    protected Player player;
    protected PlayerClass playerClass;
    protected String Name;
    public int picked_Loadout = 1;
    public enum picked_primary_attachments{
        acog,
        holographic,

    }
    public enum picked_secondary_attachments{
        holographic
    }
    public enum primary_grenade{
        frag,
        smoke
    }
    public picked_primary_attachments primary_attachments;
    public picked_secondary_attachments secondary_attachments;
    public primary_grenade picked_grenade;
    public Operator(int abC,Player p,PlayerClass pC, String name){
        this.AbilityCount = abC;
        this.player = p;
        this.playerClass = pC;
        this.Name = name;
        if(!OperatorStorage.containsValue(p)) {
            OperatorStorage.put(name.toLowerCase(), p);
        }
    }

    public abstract void HoldAbility();
    public abstract void AbilityCooldown();
    public abstract void ResetAbility();
    public abstract void SetAbility();
    public abstract void activateAbility();

    public String getName(){
        return this.Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public Player getPlayer(){
        return this.player;
    }
    public void setPlayer(Player p){
        this.player = p;
    }
    public                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          PlayerClass getPlayerClass(){
        return this.playerClass;
    }
    public void setPlayerClass(PlayerClass plc){
        this.playerClass = plc;
    }
}
