package me.R6SMC.plugin.Operators.OperatorClasses.Defense;

import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.OperatorClasses.OperatorAbilities.BanditCharge;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bandit extends Operator {
    public OperatorUtility PlayerManager;
    private final float RangeAid = 2;
    public boolean CanMove = true;
    private boolean canUseAbility = true;
    public List<BanditCharge> CurrentCharges = new ArrayList<>();
    private int AbilityCounter = 0;
    private int Charges = 4;
    private HashMap<Integer, Location[]> SoftWallLocations = new HashMap<Integer,Location[]>(){{
        //walls that can be electrified and blown up.
    }};
    private HashMap<Integer,Location> SoftWallPlaceLocations = new HashMap<Integer,Location>(){{
        //Places where the player will be teleported to while placing a charge,and where the charge will be placed.
    }};
    public Bandit(OperatorUtility ou){
        super(4,ou.getOwner(),ou.getOwnerClass(),"bandit");
        PlayerManager = ou;
        AbilityCounter = 0;
        Charges = 4;
    }

    @Override
    public void HoldAbility() {

    }

    @Override
    public void AbilityCooldown() {
        canUseAbility = false;
        if(Charges > 0) {
            Charges--;
        }else{
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                canUseAbility = true;
            }
        }.runTaskLater(GameLogic.mainThread,40);
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
            for(Location[] l : SoftWallLocations.values()){
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
        try{
            int result = PlayerIsLookingAtDoor(player);
            if(result > -1 && canUseAbility){
                Location[] SoftWall = SoftWallLocations.get(result);
                Location SoftWallPlaceLoc = SoftWallPlaceLocations.get(result);
                placeCharge(player.getLocation(),SoftWallPlaceLoc);
                player.teleport(SoftWallPlaceLoc);
                BanditCharge charge = new BanditCharge(PlayerManager,this,SoftWall,SoftWallPlaceLoc,AbilityCounter);
                AbilityCounter++;

                CurrentCharges.add(charge);
                CanMove = false;
                AbilityCooldown();
            }
        }catch (Exception e){

        }
    }
    public void placeCharge(Location Previous,Location PlaceForCharge){
        new BukkitRunnable() {
            @Override
            public void run() {
                CanMove = true;
                player.teleport(Previous);
            }
        }.runTaskLater(GameLogic.mainThread,40);
    }
}
