package me.R6SMC.plugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Overrider {


    public static void OverridePlayerMovement(boolean CanMove, Player sender){
        GameLogic.PlayersCanMove = CanMove;
        sender.sendMessage(ChatColor.GRAY + " Movement has been sucessfully overrided to " + CanMove);
    }
}
