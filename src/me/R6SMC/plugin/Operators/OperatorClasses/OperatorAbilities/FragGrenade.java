package me.R6SMC.plugin.Operators.OperatorClasses.OperatorAbilities;

import javafx.util.Pair;
import me.R6SMC.plugin.GameLogic.GameLogic;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;


public class FragGrenade extends Grenade{

    private float index = 0;
    public FragGrenade(Player owner,float damage, float weight, float TimeBeforeDetonate, EntityType type,Location l) {
        super(damage, weight, TimeBeforeDetonate, type);
        super.owner = owner;
        Grenade.currentGrenades.put(new Pair<>(GrenadeType.frag,Grenade.currentGrenades.size() + 1),this);
        index = Grenade.currentGrenades.size();
        Throw(ParticleType.follow,ParticleColor.red,l);

    }

    public FragGrenade(){
        super(10,5,3,EntityType.SNOWBALL);

    }

    @Override
    public float getRange() {
        return 5;
    }

    @Override
    public List<Object> getDefaults() {
        List<Object> def = new ArrayList<>();
        def.add(damage);
        def.add(weight);
        def.add(TimeBeforeDetonate);
        def.add(type);
        return def;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void Detonate() {
        isDestroyed = true;
        grenade.remove();
    }

    @Override
    public void Throw(ParticleType type, ParticleColor color,Location l) {
        grenade = owner.launchProjectile(Snowball.class);
        grenade.setVelocity(grenade.getVelocity().multiply(0.5));
        grenade.setFallDistance(1f);
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!isDestroyed){
                    switch (type){
                        case follow:
                            switch (color) {
                                case black:
                                    GameLogic.world.spawnParticle(Particle.ASH,grenade.getLocation(),3);
                                case red:
                                    GameLogic.world.spawnParticle(Particle.REDSTONE,grenade.getLocation(),3);
                                case green:

                            }
                    }
                }else{
                    cancel();
                }
            }
        }.runTaskTimer(GameLogic.mainThread,0,1);
        new BukkitRunnable() {
            @Override
            public void run() {
                Detonate();
            }
        }.runTaskLater(GameLogic.mainThread,(long)TimeBeforeDetonate);
    }

    @Override
    public void Follow(ParticleType type, ParticleColor color) {

    }
}
