package me.R6SMC.plugin.CommandClasses;

import javafx.util.Pair;
import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.Errors;
import me.R6SMC.plugin.Sounds.Sounds;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CommandCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("tbcm-dc")){
            if(args.length == 1) {
                List<String> Commands = new ArrayList<>(DevConsole.getCommands());
                return Commands;
            }
            if(args[0].equalsIgnoreCase("class_reload")) {
                if (args.length == 2) {
                    return new ArrayList<String>() {{
                        add("Dokkaebi");
                        add("Ash");
                        add("Capitao");
                        add("Finka");
                        add("Aruni");
                        add("Bandit");
                        add("Doc");
                        add("Rook");
                    }};
                }
            }
            if(args[0].equalsIgnoreCase("error_config")){
                    if(args.length == 2){
                        return new ArrayList<String>(){{add("reload");add("state");add("get");}};
                    }else if(args.length == 3){
                        if(args[1].equalsIgnoreCase("state")){
                            return new ArrayList<String>(){{add(Integer.toString(1));add(Integer.toString(2));add(Integer.toString(3));}};
                        }
                        if(args[1].equalsIgnoreCase("get")){
                            return new ArrayList<String>() {{
                                add("Dokkaebi");
                                add("Ash");
                                add("Capitao");
                                add("Finka");
                                add("Aruni");
                                add("Bandit");
                                add("Doc");
                                add("Rook");
                                add("Camera");
                                add("Camera_Utility");
                                add("Camera_Listener");
                            }};

                        }
                    }
                    else if(args.length == 4){
                        if(args[1].equalsIgnoreCase("get")){
                            List<String> Operators = new ArrayList<String>() {{
                                add("Dokkaebi");
                                add("Ash");
                                add("Capitao");
                                add("Finka");
                                add("Aruni");
                                add("Bandit");
                                add("Doc");
                                add("Rook");
                                add("Camera");
                                add("CameraUtility");
                                add("CameraListener");
                            }};

                            for(String st : Operators){
                                if(args[2].equalsIgnoreCase(st)){
                                    List<String> list =  new ArrayList<String>() {{
                                            Set<Pair<Class,String>> classes = Errors.Errors.keySet();
                                            for(Pair<Class,String> pair : classes){
                                                if(pair.getKey().getSimpleName().equalsIgnoreCase(st)){
                                                    add(pair.getValue());
                                                }
                                            }
                                    }};
                                    return list;
                                }
                            }
                        }
                    }
                }
            if(args[0].equalsIgnoreCase("npc")) {
                if (args.length == 2) {
                    List<String> NpcOptions = new ArrayList<String>() {{
                        add("create");
                    }};
                    return NpcOptions;
                } else if (args.length == 3) {
                    //This will just show playerNames if they want to create player named NPCS

                } else if (args.length == 4) {
                    List<String> posComplete = new ArrayList<String>() {{
                        add("L:");
                    }};
                    return posComplete;
                } else if (args.length == 5) {
                    List<String> PlayerPosX = new ArrayList<String>() {{
                        add((Double.toString(((Player) commandSender).getLocation().getX())));
                    }};
                    return PlayerPosX;
                } else if (args.length == 6) {
                    List<String> PlayerPosY = new ArrayList<String>() {{
                        add((Double.toString(((Player) commandSender).getLocation().getY())));
                    }};
                    return PlayerPosY;
                } else if (args.length == 7) {
                    List<String> PlayerPosZ = new ArrayList<String>() {{
                        add((Double.toString(((Player) commandSender).getLocation().getZ())));
                    }};
                    return PlayerPosZ;
                }
            }
            if(args[0].equalsIgnoreCase("sound")){
                if(args.length == 1) {
                    return new ArrayList<String>() {{
                        add("play");
                    }};
                }else if(args.length == 2){
                    return new ArrayList<>(Sounds.Playable_Sounds_Str.keySet());
                }

            }
        }


        return null;
    }
}
