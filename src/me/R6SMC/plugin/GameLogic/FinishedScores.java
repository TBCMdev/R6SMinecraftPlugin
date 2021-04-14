package me.R6SMC.plugin.GameLogic;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;

import java.util.List;

public class FinishedScores {

    public static String GetEndResults(@Nullable List<String> PlayerNames){//ASSUMING THAT THE LIST IS SORTED
        String endResult = ChatColor.DARK_GREEN +  "-----END RESULTS-----\n" +
                                  ChatColor.GOLD + "#1. "+PlayerNames.get(0)
                                +ChatColor.GREEN +"#2." +  PlayerNames.get(1);
        return endResult;
    }
}
