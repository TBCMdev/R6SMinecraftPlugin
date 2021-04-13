package me.R6SMC.plugin.Operators.OperatorClasses;

import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import org.bukkit.ChatColor;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Ash extends Operator {
    private boolean CanUseAbility = true;

    public Ash(OperatorUtility ou) {
        super(3,ou.getOwner(),ou.getOwnerClass(),"ash");
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
                Snowball ball = GameLogic.world.spawn(player.getEyeLocation(), Snowball.class);
                ItemMeta meta = ball.getItem().getItemMeta();
                meta.setDisplayName(name);
                AbilityCooldown();
            }else{
                player.sendMessage(ChatColor.RED + "Ability is on cooldown!");
            }
        }
    }
}
