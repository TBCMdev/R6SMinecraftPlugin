package me.R6SMC.plugin.Operators.OperatorClasses.OperatorAbilities;

import javafx.util.Pair;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Grenade {
    public static HashMap<Pair<GrenadeType,Integer>,Grenade> currentGrenades = new HashMap<>();
    public static List<Grenade> allGrenadeDefaultValues = new ArrayList<Grenade>(){{
        add(new FragGrenade());
    }};
    public Grenade(float damage,float weight,float TimeBeforeDetonate,EntityType type){
        this.damage = damage;
        this.weight = weight;
        this.TimeBeforeDetonate = TimeBeforeDetonate;
        this.type = type;
    }
    public static boolean checkForActiveGrenades(){
        if(currentGrenades.size() <= 0){
            return true;
        }

        return false;
    }
    public abstract float getRange();
    public abstract List<Object> getDefaults();
    protected double damage;
    protected float weight;
    protected float TimeBeforeDetonate;
    protected EntityType type = EntityType.SNOWBALL;
    protected Entity grenade;
    protected Location location;
    protected Player owner;
    public boolean isDestroyed = false;
    public enum GrenadeType{
        frag
    }
    public enum ParticleType{
        follow
    }
    public enum ParticleColor{
        red,
        black,
        green;
    }
    public abstract Location getLocation();
    public double getDamage(){
        return damage;
    }
    public abstract void Detonate();
    //public abstract void Detonate(Player p);
    //public abstract void Detonate(List<Player> p);
    public abstract void Throw(ParticleType type, ParticleColor color,Location l);
    public abstract void Follow(ParticleType type,ParticleColor color);
}
