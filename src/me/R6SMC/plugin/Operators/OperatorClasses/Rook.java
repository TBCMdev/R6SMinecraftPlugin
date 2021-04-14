package me.R6SMC.plugin.Operators.OperatorClasses;

import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Rook extends Operator {

    public int Boosts = 1;
    public double AbsorbtionAmount = 5;
    private boolean CanUseAbility = true;
    OperatorUtility PlayerManager;
    public Rook(OperatorUtility ou) {
        super(3, ou.getOwner(),ou.getOwnerClass(),"rook");
        PlayerManager = ou;
    }
    @Override
    public void AbilityCooldown() {
        CanUseAbility = false;
    }

    @Override
    public void ResetAbility() {
        Boosts = 1;
        CanUseAbility = true;
    }

    @Override
    public void SetAbility() {
        Boosts = 1;
    }

    @Override
    public void activateAbility() {
       if(GameLogic.GameStarted) {
            if(CanUseAbility) {
                for (Player p : GameLogic.BlueTeam) {
                    p.setAbsorptionAmount(AbsorbtionAmount);
                    p.sendMessage(ChatColor.BLUE +"Rook" + ChatColor.GREEN + " has given everyone on your team extra protection! (" + ChatColor.BLUE + "+2.5" + ChatColor.GREEN + " Absorbtion Hearts)");
                }
            AbilityCooldown();
                PlayerManager.getOwner().sendMessage(ChatColor.DARK_BLUE + "You have given your team extra protection! you can only use this once per game.");
            }

       }
    }
}
