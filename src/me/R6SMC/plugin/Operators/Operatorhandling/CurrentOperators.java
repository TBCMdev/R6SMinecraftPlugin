package me.R6SMC.plugin.Operators.Operatorhandling;

import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.Errors;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Ash;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Capitao;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Dokkaebi;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Finka;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Aruni;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Bandit;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Doc;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Rook;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrentOperators {

    public static HashMap<Player,Operator> CurrentOperators = new HashMap<>();
    public static boolean ResetOperators(){
        try{
            GameLogic.PlayerClasses.clear();
            CurrentOperators.clear();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static Operator getPlayerWithOp(Player p){
        try{
            return CurrentOperators.get(p);
        }catch (Exception e){
            p.sendMessage(Errors.getError(CurrentOperators.class,"GP_WO"/*get player with op*/));
        }
        return null;
    }
    private static List<Operator> testOperators = new ArrayList<>();
    private static boolean isRunning = false;
    public static boolean CheckToCreateTestOperator(Player p,float timeInstanceIsAlive,int i){
        if(isRunning)return false;
        if(!isRunning){
            isRunning = true;
        }
        if(timeInstanceIsAlive <= 0){
            timeInstanceIsAlive = 1200;
        }
        p.sendMessage(ChatColor.GREEN + "your operator has been created. if you did not enter " +
                "a time for it to be useable then it will expire in 60 seconds.");
        testAdd(p,i);
        new BukkitRunnable() {
            @Override
            public void run() {
                testOperators.clear();
                isRunning = false;
            }
        }.runTaskLater(GameLogic.mainThread,(long)timeInstanceIsAlive);
        return true;
    }
    public static boolean CheckToCreateOperator(Player p){
        if(GameLogic.PlayerClasses.containsKey(p.getDisplayName())){
            if(CurrentOperators.containsKey(p)){
                DevConsole.SendDevMessage(p,"Returning false.",DevConsole.TESTING);
                return false;
            }else{
                DevConsole.SendDevMessage(p,"returning true!",DevConsole.TESTING);
                return true;
            }
        }

        return false;
    }
    public static boolean Check(Player p, int Operator){

        switch (Operator) {
            case 1:
                for(Operator o : CurrentOperators.values()){
                    if(o instanceof Doc){
                        return true;
                    }
                }
                break;
            case 2:
                for(Operator o : CurrentOperators.values()){
                    if(o instanceof Rook){
                        return true;
                    }
                }
                break;
            case 3:
                for(Operator o : CurrentOperators.values()){
                    if(o instanceof Aruni){
                        return true;
                    }
                }
                break;
            case 4:
                for(Operator o : CurrentOperators.values()){
                    if(o instanceof Ash){
                        return true;
                    }
                }
                break;
            case 5:
                for(Operator o : CurrentOperators.values()){
                    if(o instanceof Finka){
                        return true;
                    }
                }
                break;
            case 6:
                for(Operator o : CurrentOperators.values()){
                    if(o instanceof Capitao){
                        return true;
                    }
                }
                break;
        }
        return false;
    }
    public static boolean Add(Player p,int Operator){

            switch (Operator) {
                case 1:
                    if(CheckToCreateOperator(p)) {
                        CurrentOperators.put(p, new Doc(OperatorHolder.GetOperator(p)));
                        DevConsole.SendDevMessage(p,ChatColor.GREEN + "Instantiating class!",DevConsole.TESTING);

                        return true;
                    }else{
                        DevConsole.SendDevMessage(p,"Operator " + Operator + " could not be instantiated because the operator Already exists!",DevConsole.TESTING);
                    }
                    break;
                case 2:
                    if(CheckToCreateOperator(p)) {
                        CurrentOperators.put(p, new Rook(OperatorHolder.GetOperator(p)));
                        DevConsole.SendDevMessage(p,ChatColor.GREEN + "Instantiating class!",DevConsole.TESTING);

                        return true;
                    }else{
                        DevConsole.SendDevMessage(p,"Operator " + Operator + " could not be instantiated because the operator Already exists!",DevConsole.TESTING);
                    }
                    break;
                case 3:
                    if(CheckToCreateOperator(p)) {
                        CurrentOperators.put(p, new Aruni(OperatorHolder.GetOperator(p)));
                        DevConsole.SendDevMessage(p,ChatColor.GREEN + "Instantiating class!",DevConsole.TESTING);
                        return true;
                    }else{
                        DevConsole.SendDevMessage(p,"Operator " + Operator + " could not be instantiated because the operator Already exists!",DevConsole.TESTING);
                    }
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

        return false;
    }
    public static boolean testAdd(Player p,int Operator){

        switch (Operator) {
            case 1:
                    testOperators.add(new Doc(OperatorHolder.GetOperator(p)));
                    DevConsole.SendDevMessage(p,ChatColor.GREEN + "Instantiating class!",DevConsole.TESTING);

                    return true;
            case 2:
                    testOperators.add(new Rook(OperatorHolder.GetOperator(p)));
                    DevConsole.SendDevMessage(p,ChatColor.GREEN + "Instantiating class!",DevConsole.TESTING);

                    return true;
            case 3:
                    testOperators.add(new Aruni(OperatorHolder.GetOperator(p)));
                    DevConsole.SendDevMessage(p,ChatColor.GREEN + "Instantiating class!",DevConsole.TESTING);
                    return true;
            case 4:
                    testOperators.add(new Ash(OperatorHolder.GetOperator(p)));
                    return true;
            case 5:
                    testOperators.add(new Finka(OperatorHolder.GetOperator(p)));
                    return true;
            case 6:
                testOperators.add(new Capitao(OperatorHolder.GetOperator(p)));
                return true;
            case 7:
                testOperators.add(new Bandit(OperatorHolder.GetOperator(p)));
                return true;
            case 8:
                testOperators.add(new Dokkaebi(OperatorHolder.GetOperator(p)));
                return true;
        }

        return false;
    }
}
