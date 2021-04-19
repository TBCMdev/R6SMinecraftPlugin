package me.R6SMC.plugin.CommandClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Commands {
    //THIS ONLY HOLDS 1 ARG but you can parse the second/third.
    private static HashMap<String,String[]> CommandArgs = new HashMap<String,String[]>(){{
        put("start",new String[0]);
        put("jointeamred",new String[0]);
        put("jointeamblue",new String[0]);
        put("ability",new String[0]);
        put("attkoperator",new String[]{"ash","capitao","finka"});
        put("defoperator",new String[]{"doc","rook", "Aruni"});
        put("override",new String[]{"movement || true | false"});
        put("showPageBlue",new String[]{"default | 0","doc | 1","rook | 2", "Aruni | 3"});
        put("showPageRed",new String[]{"default | 0","ash | 1","capitao | 2","finka | 3"});
    }};
    private static String[] COMMANDS;
    private static void InitializeCommands(){
        CommandArgs.keySet().toArray(COMMANDS);
    }
    public static String GetCommand(int index){
        return COMMANDS[index];
    }
    public static boolean CheckCommandArg(String argument,String TrueValue,String FalseValue){
        if(argument.equalsIgnoreCase(TrueValue)){
            return true;
        }
        if(argument.equalsIgnoreCase(FalseValue)){
            return false;
        }
        return false;
    }
    public static List<String> GetCommands(String[] ExceptCommands){
        List<String> retCommands = new ArrayList<>();
        for(String s : CommandArgs.keySet()){
            for(String st : ExceptCommands){
                if(s != st){
                    retCommands.add(s);
                }
            }
        }
        return retCommands;
    }
}
