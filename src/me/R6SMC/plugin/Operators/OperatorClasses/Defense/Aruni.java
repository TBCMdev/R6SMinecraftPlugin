package me.R6SMC.plugin.Operators.OperatorClasses.Defense;

import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import me.R6SMC.plugin.PlayerLogic.PlayerClass;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

import java.util.HashMap;

public class Aruni extends Operator {

    private HashMap<Integer, Location[]> DoorLocations = new HashMap<>();//ADD door locations
    private boolean CanUseAbility = true;
    public OperatorUtility PlayerManager;
    public Aruni(OperatorUtility ou) {
        super(3,ou.getOwner(),ou.getOwnerClass(),"aruni");
        PlayerManager = ou;
    }

    @Override
    public void HoldAbility() {
        BlockIterator iter = new BlockIterator(player, 5);
        new BukkitRunnable() {
            @Override
            public void run() {
                while (iter.hasNext()) {
                    Block current = iter.next();
                    PlayerManager.getOwner().spawnParticle(Particle.FLAME, current.getLocation(), 2);
                }
            }
        }.runTaskTimer(GameLogic.mainThread,0,10);


    }
    @Override
    public void AbilityCooldown() {
        CanUseAbility = false;
        new BukkitRunnable(){

            @Override
            public void run() {
                CanUseAbility = true;
            }
        }.runTaskLater(GameLogic.mainThread,20L);
    }

    @Override
    public void ResetAbility() {

    }

    @Override
    public void SetAbility() {

    }
    @Override
    public void activateAbility() {
        BlockIterator iter = new BlockIterator(player,5);
        while(iter.hasNext()){
            Block current = iter.next();

            if(current.getType() == Material.AIR){
                for(Location[] l : DoorLocations.values()){
                    for(Location lo : l){
                        Block CurrentBlock = lo.getBlock();
                        if(CurrentBlock.getLocation() == current.getLocation()){

                        }
                    }
                }
            }
        }
    }
}
