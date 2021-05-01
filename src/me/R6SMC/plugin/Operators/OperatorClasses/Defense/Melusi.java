package me.R6SMC.plugin.Operators.OperatorClasses.Defense;

import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.Operators.OperatorClasses.OperatorAbilities.Banshii;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Melusi extends Operator {
    private Material melusiGadget;
    public List<Banshii> placedGadets = new ArrayList<>();
    public Melusi(OperatorUtility ou){
        super(3,ou.getOwner(),ou.getOwnerClass(),"melusi");
    }
    @Override
    public void HoldAbility() {

    }
    public void removeGadget(Banshii b){
        try{
            placedGadets.remove(b);
        }catch (Exception e){
            DevConsole.SendDevMessage(player,"cannot remove banshii.",DevConsole.TESTING);
        }
    }
    @Override
    public void AbilityCooldown() {

    }
    public void DestroyAbility(int index){
        try{
            placedGadets.get(index).Destroy();
        }catch (Exception e){
            player.sendMessage(ChatColor.RED + "Could not destroy Banshii.");
        }
    }
    @Override
    public void ResetAbility() {

    }
    @Override
    public void SetAbility() {

    }
    private boolean CheckToPlace(Block current){
        if(current.getType().isAir() && !current.getLocation().subtract(0,1,0).getBlock().getType().isAir()){
            return true;
            //90% should return floor block
        }else{
            if(CheckForWall(current.getLocation().subtract(0,1,0).getBlock())){
                return true;
                //80% should return wall block
            }
        }
        return false;
    }
    private boolean CheckForWall(Block current){
        if(player.getLocation().getYaw() >= -25){
            return true;
        }
        return false;
    }
    public Banshii getBanshiiByLoc(Location l){
        for(Banshii b : placedGadets){
            if(b.getLocation().equals(l)){
                return b;
            }
        }
        return null;
    }
    @Override
    public void activateAbility() {
        Block current = player.getTargetBlock((Set<Material>) null, 5).getLocation().add(0,1,0).getBlock();
        if(CheckToPlace(current)){
            placedGadets.add(new Banshii(current.getLocation(),0,player,melusiGadget));
        }
    }
}
