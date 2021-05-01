package me.R6SMC.plugin.Operators.OperatorClasses.Defense;

import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.OperatorClasses.OperatorAbilities.BanditCharge;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Bandit extends Operator {
    public OperatorUtility PlayerManager;
    private final float RangeAid = 2;
    public boolean CanMove = true;
    private boolean canUseAbility = true;
    public static List<BanditCharge> CurrentCharges = new ArrayList<>();
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
    public static void ReloadClass(){
        for(BanditCharge b : CurrentCharges){
            b.Destroy();
        }
        CurrentCharges.clear();
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
    public int PlayerIsLookingAtDoor() {
        Block current = player.getTargetBlock((Set<Material>) null, 5).getLocation().add(0, 1, 0).getBlock();
        Location currentPos = current.getLocation();
        List<Location[]> locations = new ArrayList<>(SoftWallLocations.values());
        Location[] nearestDoor = locations.get(0);
        double minvalue = Integer.MAX_VALUE;
        for (Location[] l : SoftWallLocations.values()) {
            for(Location lo : l) {
                if(!SoftWallPlaceLocations.containsValue(lo)){
                    player.sendMessage(ChatColor.RED + "you are not looking at a valid place to use a charge!");
                    return -1;
                }

            }
                Bukkit.getLogger().info(l.toString());
                double value = Math.sqrt(Math.pow((l[0].getX() - currentPos.getX()), 2) + Math.pow((l[0].getY() - currentPos.getY()), 2) + Math.pow((l[0].getZ() - currentPos.getZ()), 2));
                if (value < minvalue) {
                    minvalue = value;
                    nearestDoor = l;
                    player.sendMessage("value: " + ChatColor.GOLD + minvalue + "door index:" + locations.indexOf(nearestDoor));
                }


        }
        if (minvalue < RangeAid) {
            return locations.indexOf(nearestDoor);
        } else {
            player.sendMessage(ChatColor.RED + "Door already electrified.");
        }
        return -1;
    }
    @Override
    public void activateAbility() {
        try{
            int result = PlayerIsLookingAtDoor();
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
