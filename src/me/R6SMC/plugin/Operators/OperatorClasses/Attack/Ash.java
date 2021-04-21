package me.R6SMC.plugin.Operators.OperatorClasses.Attack;

import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Ash extends Operator {
    public final Material[] BlocksToDestroy = {Material.DARK_OAK_SLAB};
    private boolean CanUseAbility = true;
    public OperatorUtility PlayerManager;
    public Ash(OperatorUtility ou) {
        super(3,ou.getOwner(),ou.getOwnerClass(),"ash");
        PlayerManager = ou;
    }
    public static void ReloadClass(){

    }
    @Override
    public void HoldAbility() {

    }

    @Override
    public void AbilityCooldown() {
        CanUseAbility = false;
        new BukkitRunnable(){

            @Override
            public void run() {
                CanUseAbility = true;
            }
        }.runTaskLater(GameLogic.mainThread,60L);
    }

    @Override
    public void ResetAbility() {

    }

    @Override
    public void SetAbility() {

    }

    @Override
    public void activateAbility() {
        if(GameLogic.GameStarted) {
            if(CanUseAbility) {

                String name = "ASHABIL";
                Snowball ball = PlayerManager.getOwner().launchProjectile(Snowball.class);
                ball.setShooter(PlayerManager.getOwner());
                ball.setVelocity(ball.getVelocity().multiply(2));
                ball.setGravity(false);
                ball.setCustomName(name);
                AbilityCooldown();
            }else{
                player.sendMessage(ChatColor.RED + "Ability is on cooldown!");
            }
        }
    }
}
