package me.R6SMC.plugin.Operators;

import me.R6SMC.plugin.DevConsole;
import me.R6SMC.plugin.GameLogic;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CurrentOperators {

    public static HashMap<Player,Operator> CurrentOperators = new HashMap<>();

    public static boolean CheckToCreateOperator(Player p){
        if(GameLogic.PlayerClasses.containsKey(p.getDisplayName())){
            if(CurrentOperators.containsKey(p)){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
    public static boolean Add(Player p,int Operator){
        if(CurrentOperators.containsKey(p)){
            DevConsole.SendDevMessage(p,"could not add player and operator key set to CLASS:" + ChatColor.GREEN + " CurrentOperators.",DevConsole.TESTING);
            return false;
        }else{
            switch (Operator) {
                case 1:
                    if(CheckToCreateOperator(p)) {
                        CurrentOperators.put(p, new Doc(OperatorHolder.GetOperator(p)));
                        return true;
                    }else{
                        DevConsole.SendDevMessage(p,"Operator " + Operator + " could not be instantiated because the operator Already exists!",DevConsole.TESTING);
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    if(CheckToCreateOperator(p)) {
                        CurrentOperators.put(p, new Ash(OperatorHolder.GetOperator(p)));
                        return true;
                    }
                    else{
                        DevConsole.SendDevMessage(p,"Operator " + Operator + " could not be instantiated because the operator Already exists!",DevConsole.TESTING);
                    }
                    break;
                case 5:
                    if(CheckToCreateOperator(p)) {
                        CurrentOperators.put(p, new Finka(OperatorHolder.GetOperator(p)));
                        return true;
                    }
                    else{
                        DevConsole.SendDevMessage(p,"Operator " + Operator + " could not be instantiated because the operator Already exists!",DevConsole.TESTING);
                    }
                    break;
                case 6:
                    break;
            }
        }
        return false;
    }
}
