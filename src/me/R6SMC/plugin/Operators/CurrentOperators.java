package me.R6SMC.plugin.Operators;

import me.R6SMC.plugin.DevConsole;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CurrentOperators {

    public static HashMap<Player,Operator> CurrentOperators = new HashMap<>();


    public static void Add(Player p,int Operator){
        if(CurrentOperators.containsKey(p)){
            DevConsole.SendDevMessage(p,"could not add player and operator key set to CLASS:" + ChatColor.GREEN + " CurrentOperators.",DevConsole.TESTING);
            return;
        }else{
            switch (Operator) {
                case 1:
                    CurrentOperators.put(p, new Doc(OperatorHolder.GetOperator(p)));
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    CurrentOperators.put(p, new Ash(OperatorHolder.GetOperator(p)));
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
        }
    }
}
