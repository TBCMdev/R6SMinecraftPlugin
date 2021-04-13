package me.R6SMC.plugin.Operators.OperatorClasses;

import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Finka extends Operator {
    public int Boosts = 3;
    public Finka(OperatorUtility ou) {
        super(3, ou.getOwner(),ou.getOwnerClass(),"finka");
    }

    @Override
    public void ResetAbility() {
        Boosts = 3;
    }

    @Override
    public void SetAbility() {
        Boosts = 3;
    }

    @Override
    public void activateAbility() {

        for(Player p : GameLogic.RedTeam){
            if(GameLogic.GameStarted){
                p.setAbsorptionAmount(2);
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player pl : GameLogic.RedTeam){
                    pl.setAbsorptionAmount(0);
                }
            }
        }.runTaskLater(GameLogic.mainThread,100L);
    }
}
