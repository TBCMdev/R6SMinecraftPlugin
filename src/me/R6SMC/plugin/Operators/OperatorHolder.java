package me.R6SMC.plugin.Operators;

import me.R6SMC.plugin.DevConsole;
import me.R6SMC.plugin.GameChat;
import me.R6SMC.plugin.GameLogic;
import me.R6SMC.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static org.bukkit.Bukkit.getLogger;

public class OperatorHolder {
    public static HashMap<Player,OperatorUtility> operators = new HashMap<>();



    public static OperatorUtility GetOperator(Player p){
        OperatorUtility operator;
        try{
            if(operators.containsKey(p)) {
                return operators.get(p);
            }else{
                operator = new OperatorUtility(p, GameLogic.PlayerClasses.get(p.getDisplayName()));

                operators.put(p,operator);
                return operator;
            }
        }
        catch (Exception e){
            DevConsole.SendDevMessage(p,"Could not retrieve operator",DevConsole.TESTING);
        }

        return null;

   }
}
