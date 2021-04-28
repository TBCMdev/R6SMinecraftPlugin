package me.R6SMC.plugin;

import com.mojang.datafixers.types.Func;
import com.sun.istack.internal.NotNull;
import javafx.util.Pair;
import me.R6SMC.plugin.Cameras.Camera;
import me.R6SMC.plugin.Cameras.CameraUtility;
import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.Listeners.CameraListener;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Ash;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Capitao;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Dokkaebi;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Finka;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Aruni;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Bandit;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Doc;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Rook;
import me.R6SMC.plugin.Operators.Operatorhandling.CurrentOperators;
import org.apache.logging.log4j.core.util.KeyValuePair;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class Errors {
    private static String r = "" + ChatColor.RED;
    private static int ErrorUsage = 1;
    public static Map<String,Integer> ErrorIndexes = new HashMap<String,Integer>(){{
        put("*R_E", 1);
        put("*ABIL_P", 2);
        put("*ABIL_S", 3);
        put("*U_EX", 4);
        put("*IN_DE", 5);
        put("*IN_AE", 6);
        put("*CF_A", 7);;
        put("*CF_L", 8);
        put("*NP_UC", 9);
        put("*NPC_C", 10);
        put("*NPC_R", 11);
        put("*PN_AC", 12);
        put("*CL_C", 13);
        put("*GC_BN",14);
        put("*CP_F",15);
    }};
    public static Map<Pair<Class,String>, String> Errors = new HashMap<Pair<Class,String>,String>(){{
        //region ReloadErrors
        put(new Pair<>(Dokkaebi.class,"*R_E"/*reload_error*/),"Dokkaebi Reload Error. try entering a correct class name or reloading the class." + r);
        put(new Pair<>(Doc.class,"*R_E"),"doc Reload Error. try entering a correct class name or reloading the class." + r);
        put(new Pair<>(Rook.class,"*R_E"),"Rook Reload Error. try entering a correct class name or reloading the class." + r);
        put(new Pair<>(Aruni.class,"*R_E"),"Aruni Reload Error. try entering a correct class name or reloading the class." + r);
        put(new Pair<>(Ash.class,"*R_E"),"Ash Reload Error. try entering a correct class name or reloading the class." + r);
        put(new Pair<>(Finka.class, "*R_E"), "Finka Reload Error. try entering a correct class name or reloading the class." + r);
        put(new Pair<>(Capitao.class,"*R_E"),"Capitao Reload Error. try entering a correct class name or reloading the class." + r);
        //endregion
        //region Dokkaebi
        put(new Pair<>(Dokkaebi.class,"*ABIL_P"/*ability_player*/),"Dokkaebi Ability Error with player receiving Call." + r);
        put(new Pair<>(Dokkaebi.class,"*ABIL_S"/*ability_self*/),"Dokkaebi Ability Error from her player. " + r);
        put(new Pair<>(Dokkaebi.class,"*U_EX"/*unknown_exeption*/),"Dokkaebi Exeption." + r);
        //endregion
        //region Ash
        put(new Pair<>(Ash.class,"*ABIL_W"),"Ash Ability Error at Destroying wall." + r);
        //endregion
        //region Camera
        put(new Pair<>(Camera.class,"*IN_DE"),"Camera error while leaving camera. Index Doesnt Exist. " + r);
        put(new Pair<>(Camera.class,"*IN_AE"),"Camera error while accessing camera. Index Already exists. " + r);
        put(new Pair<>(Camera.class,"*CF_L"),"Camera error while Initiating leave camera feed." + r);
        put(new Pair<>(Camera.class,"*CF_A"),"Camera error while Initiating activate camera feed" + r);
        put(new Pair<>(Camera.class,"*NPC_C"),"Camera error while creating NPC. " + r);
        put(new Pair<>(Camera.class,"*NP_UC"),"Camera error. No player using camera. "  +r);
        put(new Pair<>(Camera.class,"*NPC_R"),"Camera error while trying to remove an npc. " + r);
        put(new Pair<>(CameraListener.class,"*PN_AC"),"CameraListener error. Player did not get added to cooldown. " + r);
        put(new Pair<>(CameraListener.class,"*CL_C"),"CameraListener error. Player cannot leave camera. " + r);
        //endregion
        put(new Pair<>(CurrentOperators.class,"*CG_O"),"Cannot get operator." + r);
        put(new Pair<>(CurrentOperators.class,"*CI_O"),"Cannot instantiate operator.");
        //region devconsole
        put(new Pair<>(DevConsole.class,"*CP_F"),"DevConsole error. Could not parse float." + r);
        //endregion
        //region Error_Fails
        put(new Pair<>(Errors.class,"*GN_BN"),"Error class could not retrieve error class by name." + r);
        //end region
    }};
    public static Type getClassTypeByClass(Class c,CommandSender sender){
        try{
            Type t = c;
            return t;
        } catch (Exception e) {
            sender.sendMessage(Errors.get(new Pair<Class,String>(Errors.class,"*GC_BN")));
        }
        return null;
    }
    public static Class getClassByName(String name, CommandSender sender){
        try{
            Class<?> c = Class.forName(name);
            return c;
        } catch (ClassNotFoundException e) {
            sender.sendMessage(Errors.get(new Pair<Class,String>(Errors.class,"*GC_BN")));
        }
        return null;
    }
    public static boolean getClassReloadFunc(String ClassName){
        switch (ClassName.toLowerCase()){
            case "doc":
                try{
                    Doc.ReloadClass();
                    return true;
                }catch (Exception e){
                    return false;
                }
            case "rook":
                try{
                    Rook.ReloadClass();
                    return true;
                }catch (Exception e){
                    return false;
                }
            case "aruni":
                try{
                    Aruni.ReloadClass();
                    return true;
                }catch (Exception e){
                    return false;
                }
            case "ash":
                try{
                    Ash.ReloadClass();
                    return true;
                }catch (Exception e){
                    return false;
                }
            case "finka":
                try{
                    Finka.ReloadClass();
                    return true;
                }catch (Exception e){
                    return false;
                }
            case "capitao":
                try{
                    Capitao.ReloadClass();
                    return true;
                }catch (Exception e){
                    return false;
                }
            case "bandit":
                try{
                    Bandit.ReloadClass();
                    return true;
                }catch (Exception e){
                    return false;
                }
        }
        return false;
    }
    public static String getError(@NotNull Class type, String indexer){
        try{
            return Errors.get(new Pair<>(type,indexer.toUpperCase())) + " Error Index:" + ErrorIndexes.get(indexer);
        }catch (Exception e){
            return Errors.get(new Pair<Class,String>(Errors.class,"*GN_BN"));
        }
    }
    public static String getError(@NotNull Class type, String indexer,int LineOfExpectedError){
        try{
            return Errors.get(new Pair<>(type,indexer.toUpperCase())) +" at line: "+ LineOfExpectedError+ ", Error Index:" + ErrorIndexes.get(indexer);
        }catch (Exception e){
        }
        return null;
    }
    public static void sendToAdmins(String message){

    }
    public static void setErrorUsage(int index, Player sender){
        ErrorUsage = index;
        sender.sendMessage(ChatColor.GREEN + "Error Usage has been set to " + ErrorUsage);
    }
    public static boolean CheckToBroadcast(){
        if(ErrorUsage == 2){
            return true;
        }
        return false;
    }
    public static boolean CheckToSendToAdmins(){
        if(ErrorUsage == 1){
            return true;
        }
        return false;
    }


}
