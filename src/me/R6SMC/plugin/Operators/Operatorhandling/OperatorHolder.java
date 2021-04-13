package me.R6SMC.plugin.Operators.Operatorhandling;

import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import org.bukkit.entity.Player;

import java.util.HashMap;

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
