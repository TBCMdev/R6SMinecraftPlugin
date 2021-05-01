package me.R6SMC.plugin.Operators.OperatorClasses.Defense;

import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import me.R6SMC.plugin.PlayerLogic.PlayerClass;
import me.R6SMC.plugin.Sounds.Sounds;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Doc extends Operator {

    public int Stims;
    private boolean CanUseAbility = true;
    public Doc(OperatorUtility ou){
        super(3,ou.getOwner(),ou.getOwnerClass(),"doc");
        player = ou.getOwner();
        playerClass = ou.getOwnerClass();
        Stims = 3;
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
        }.runTaskLater(GameLogic.mainThread,20L);
    }

    @Override
    public void ResetAbility() {
        Stims = 3;
    }

    @Override
    public void SetAbility() {
        Stims = 3;
    }

    @Override
    public void activateAbility() {
        if(GameLogic.GameStarted) {
                if(CanUseAbility) {
                    Sounds.PlaySound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, player);
                    //if(p.getHealth() <= (double)10 && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == "ability" && stims > 0) {
                    if (player.getHealth() <= (double) 10 && player.getInventory().getItemInMainHand().getType() == Material.WHITE_STAINED_GLASS_PANE && Stims > 0) {
                        Stims--;
                        GameChat.sendActionbar(player, ChatColor.DARK_BLUE + "**you activated docs ability!! you have " + Stims + " stim shots left**");
                        player.setHealth(player.getHealth() + (double) 10);
                        AbilityCooldown();
                    } else if (player.getInventory().getItemInMainHand().getType() == Material.WHITE_STAINED_GLASS_PANE && Stims > 0) {
                        GameChat.sendActionbar(player, ChatColor.DARK_BLUE + "**you activated docs ability!! you have " + Stims + " stim shots left**");
                        Stims--;
                        player.setHealth((double) 20);
                        AbilityCooldown();
                    } else {
                        player.sendMessage(ChatColor.RED + "you are out of stim shots!");
                    }
                }else{
                    player.sendMessage(ChatColor.RED + "Ability is on CoolDown!!");
                }
        }
    }
}
