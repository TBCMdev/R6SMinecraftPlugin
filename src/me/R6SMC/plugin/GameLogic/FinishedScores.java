package me.R6SMC.plugin.GameLogic;

import me.R6SMC.plugin.DevConsole.DevConsole;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FinishedScores {

    public static String GetEndResults(@Nullable List<String> PlayerNames){//ASSUMING THAT THE LIST IS SORTED AND HAS 5+ VALUES
        String endResult = "";
        try {
            if(PlayerNames.size() >= 5) {
                endResult = ChatColor.DARK_GREEN + "-----END RESULTS-----\n" +
                        ChatColor.GOLD + "#1. " + PlayerNames.get(0) + "\n"
                        + ChatColor.GREEN + "#2." + PlayerNames.get(1) + "\n"
                        + ChatColor.GREEN + "#3." + PlayerNames.get(2) + "\n"
                        + ChatColor.GREEN + "#4." + PlayerNames.get(3) + "\n"
                        + ChatColor.GREEN + "#5." + PlayerNames.get(4) + "\n"
                        + ChatColor.DARK_GREEN + "-----END RESULTS-----\n";
            }else{
                List<String> EndResultWithLess = new ArrayList<>(PlayerNames);
                endResult = CreateEndResultsWithLess(EndResultWithLess);
            }
            return endResult;
        }catch (Exception e){
        }
        return endResult;
    }
    private static String CreateEndResultsWithLess(List<String> PlayerNames){
        StringBuilder endResult = new StringBuilder(ChatColor.DARK_GREEN + "------END RESULTS-----\n");
        int index = 0;
        for(String s : PlayerNames){
            if(index < 5) {
                index++;
                endResult.append("\n#").append(index).append(".").append(s).append("\n");
            }
        }
        endResult.append(ChatColor.DARK_GREEN + "\n------END RESULTS-----");
        return endResult.toString();
    }
}
