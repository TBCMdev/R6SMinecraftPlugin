package me.R6SMC.plugin.Operators;

import me.R6SMC.plugin.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Doc extends Operator {

    public Player player;
    public PlayerClass playerClass;
    public String ClassName = "doc";
    public int Stims = 3;
    public Doc(OperatorUtility ou){
        super(3,ou.getOwner(),ou.getOwnerClass(),"doc");
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
            if (playerClass.Name == player.getName().toString()) {
                Sounds.PlaySound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, player);
                //if(p.getHealth() <= (double)10 && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == "ability" && stims > 0) {
                if (player.getHealth() <= (double) 10 && player.getInventory().getItemInMainHand().getType() == Material.WHITE_STAINED_GLASS_PANE && Stims > 0) {
                    Stims--;
                    GameChat.sendActionbar(player, ChatColor.DARK_BLUE + "**you activated docs ability!! you have " + Stims + " stim shots left**");
                    player.setHealth(player.getHealth() + (double) 10);
                } else if (player.getInventory().getItemInMainHand().getType() == Material.WHITE_STAINED_GLASS_PANE && Stims > 0) {
                    GameChat.sendActionbar(player, ChatColor.DARK_BLUE + "**you activated docs ability!! you have " + Stims + " stim shots left**");
                    Stims--;
                    player.setHealth((double) 20);
                } else {
                    player.sendMessage(ChatColor.RED + "you are out of stim shots!");
                }
            }
        }
    }
}
