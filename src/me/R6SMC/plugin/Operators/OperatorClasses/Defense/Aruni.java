package me.R6SMC.plugin.Operators.OperatorClasses.Defense;

import javafx.util.Pair;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import me.R6SMC.plugin.PlayerLogic.PlayerClass;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

import java.util.HashMap;

public class Aruni extends Operator {
    private final float RangeAid = 3;
    private HashMap<Integer, Location[]> DoorLocations = new HashMap<>();//ADD door locations
    private boolean CanUseAbility = true;
    private boolean ShowParticles = true;
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
                    if(ShowParticles) {
                        Block current = iter.next();
                        PlayerManager.getOwner().spawnParticle(Particle.FLAME, current.getLocation(), 2);
                    }else{
                        cancel();
                        return;
                    }
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

    public int PlayerIsLookingAtDoor(Player p){
        BlockIterator Checker = new BlockIterator(p,5);
        while(Checker.hasNext()){
            Block current = Checker.next();
            Location currentPos = current.getLocation();
            for(Location[] l : DoorLocations.values()){
                for(int i = 0; i < l.length;i++) {
                    if (currentPos.getX() <= l[i].getX() + RangeAid || currentPos.getX() >= l[i].getX() - RangeAid
                            && currentPos.getY() <= l[i].getY() + RangeAid || currentPos.getY() >= l[i].getY() - RangeAid
                            && currentPos.getZ() <= l[i].getZ() + RangeAid || currentPos.getZ() >= l[i].getZ() - RangeAid) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    @Override
    public void activateAbility() {
        if(GameLogic.GameStarted) {
            if(CanUseAbility) {
                ShowParticles = false;
                BlockIterator iter = new BlockIterator(player, 5);
                while (iter.hasNext()) {
                    Block current = iter.next();
                    if (current.getType() == Material.AIR) {
                        int index = PlayerIsLookingAtDoor(PlayerManager.getOwner());
                        Location[] l = DoorLocations.get(index);
                        Location Block = l[index];
                    }
                }
            }else{
                PlayerManager.getOwner().sendMessage(ChatColor.RED + "you are out of Aruni Gadgets!");
            }
        }

    }
}
