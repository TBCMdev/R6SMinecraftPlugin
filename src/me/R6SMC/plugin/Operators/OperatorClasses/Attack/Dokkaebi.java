package me.R6SMC.plugin.Operators.OperatorClasses.Attack;

import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import me.R6SMC.plugin.PlayerLogic.PlayerClass;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Dokkaebi extends Operator {
    private ItemStack CallIndicator = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
    private boolean canUseAbility = true;
    private int AbilityCount = 2;
    public Dokkaebi(OperatorUtility ou) {
        super(2,ou.getOwner(),ou.getOwnerClass(),"dokkaebi");
    }

    @Override
    public void HoldAbility() {

    }

    @Override
    public void AbilityCooldown() {

    }

    @Override
    public void ResetAbility() {

    }

    @Override
    public void SetAbility() {

    }
    public static void ReloadClass(){

    }
    @Override
    public void activateAbility() {
        CallIndicator.getItemMeta().setDisplayName("DOKKAEBI CALL");
        if(GameLogic.GameStarted){
            if(canUseAbility){
                for(Player p : GameLogic.BlueTeam){
                    p.getInventory().setItem(8,CallIndicator);
                    GameLogic.PlayerClasses.get(p.getDisplayName()).dokkaebiCall();
                }
            }
        }
    }
}
