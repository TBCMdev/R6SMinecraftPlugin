package me.R6SMC.plugin.Operators.OperatorClasses.Attack;

import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.block.data.type.Fire;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;

public class Capitao extends Operator {
    private boolean CanUseAbility = true;
    public OperatorUtility PlayerManager;
    public float FireIntensity;
    private float FireCanisters = 2;
    public Capitao(OperatorUtility ou) {
        super(3, ou.getOwner(),ou.getOwnerClass(),"finka");
        PlayerManager = ou;
    }

    @Override
    public void HoldAbility() {

    }

    @Override
    public void AbilityCooldown() {
        FireCanisters--;
        CanUseAbility = false;
        new BukkitRunnable() {
            @Override
            public void run() {
                CanUseAbility = true;
            }
        }.runTaskLater(GameLogic.mainThread,40L);
    }

    @Override
    public void ResetAbility() {
        FireCanisters = 2;
        CanUseAbility = true;
    }

    @Override
    public void SetAbility() {
        FireCanisters = 2;
    }

    @Override
    public void activateAbility() {
        //ONLY FIRE ABIL IMPLEMENTED
        if(GameLogic.GameStarted){
            if(CanUseAbility){
                if(FireCanisters > 0) {
                    Snowball ball = PlayerManager.getOwner().launchProjectile(Snowball.class);
                    ball.setCustomName("CAPITAOABIL");
                    ball.setShooter(PlayerManager.getOwner());
                    ball.setVelocity(ball.getVelocity().multiply(2));
                    ball.setGravity(false);
                    AbilityCooldown();
                }else{
                    PlayerManager.getOwner().sendMessage(ChatColor.RED + "you are out of fire canisters!");
                }
            }else{
                PlayerManager.getOwner().sendMessage(ChatColor.RED + "Ability is on cooldown!");
            }
        }
    }
}
