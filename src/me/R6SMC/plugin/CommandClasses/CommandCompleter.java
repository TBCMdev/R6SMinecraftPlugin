package me.R6SMC.plugin.CommandClasses;

import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.DevConsole.DevConsole;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("tbcm-dc")){
            if(args.length == 1) {
                List<String> Commands = new ArrayList<>(DevConsole.getCommands());
                return Commands;
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
        }


        return null;
    }
}
