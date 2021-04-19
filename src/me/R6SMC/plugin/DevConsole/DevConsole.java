package me.R6SMC.plugin.DevConsole;

import me.R6SMC.plugin.CommandClasses.Commands;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.GameLogic.PlayerEntities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DevConsole implements CommandExecutor {
    public static boolean TESTING = true;
    public static boolean KEEPAMMO = false;
    private final static String DevPrefix = ChatColor.RED + "*DEV CONSOLE* " + ChatColor.GRAY;
    private final static String DevTestingPrefix = ChatColor.DARK_RED + "*T* " + ChatColor.GRAY;
    public static void SendDevMessage(Player dev,String Content,boolean testing){
        if(testing) {
            dev.sendMessage(DevPrefix + DevTestingPrefix + Content);
        }else{
            dev.sendMessage(DevPrefix + Content);
        }
    }
    public static List<String> getCommands(){
        return new ArrayList<String>(){{add("testing");add("keepammo");add("npc");}};
    }
    public static boolean GetTester(){
        return TESTING;
    }
    public static String GetDevPrefix(){
        return DevPrefix;
    }
    public static String GetDevTestingPrefix(){
        return DevTestingPrefix;
    }
    public static void BroadCastDevMessage(String Content){
        if(DevConsole.TESTING) {
            Bukkit.broadcastMessage(DevPrefix + DevTestingPrefix + Content);
        }else{
            Bukkit.broadcastMessage(DevPrefix + Content);
        }
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if(args.length > 0) {
            if (command.getName().equalsIgnoreCase("TBCM-DC"))
            {
                if (args[0].equalsIgnoreCase("TESTING")) {
                    boolean argsResult = Commands.CheckCommandArg(args[1],"true","false");
                    DevConsole.TESTING = argsResult;
                    SendDevMessage(p,"TESTING universal boolean has been set to " + ChatColor.RED + argsResult,TESTING);
                }
                if (args[0].equalsIgnoreCase("KEEPAMMO")) {
                    boolean argsResult = Commands.CheckCommandArg(args[1],"true","false");
                    DevConsole.KEEPAMMO = argsResult;
                    SendDevMessage(p,"Keep Ammo has been set to " + ChatColor.RED + argsResult,TESTING);
                }
                if(args[0].equalsIgnoreCase("npc")){
                    if(args[1] != null){
                        if(args[1].equalsIgnoreCase("create")){
                            if(args[2] != null){
                                String name = args[2];
                                if(args[3].equalsIgnoreCase("L: ")){
                                    if(args[4] != null && args[5] != null && args[6] != null){
                                        try{
                                            Location l = new Location(GameLogic.world,Integer.parseInt(args[3]),Integer.parseInt(args[4]),Integer.parseInt(args[5]));
                                            PlayerEntities.CreateEntity((Player) commandSender,l);
                                        }catch (Exception e){
                                            ((Player)commandSender).sendMessage(ChatColor.RED + "Could not Instantiate Entity.");
                                        }

                                    }else{

                                        ((Player)commandSender).sendMessage(ChatColor.RED + "*ERROR* Usage: " + ChatColor.GRAY + "/tbcm-dc create [Name] L: [X] [Y] [Z]");
                                    }
                                 }else{
                                    ((Player)commandSender).sendMessage(ChatColor.RED + "*ERROR* Usage: " + ChatColor.GRAY + "/tbcm-dc create [Name] L: [X] [Y] [Z]");
                                }
                            }else{
                                ((Player)commandSender).sendMessage(ChatColor.RED + "*ERROR* Usage: " + ChatColor.GRAY + "/tbcm-dc create [Name] L: [X] [Y] [Z]");
                            }
                        }else{
                            ((Player)commandSender).sendMessage(ChatColor.RED + "*ERROR* Usage: " + ChatColor.GRAY + "/tbcm-dc create [Name] L: [X] [Y] [Z]");
                        }
                    }else{
                        ((Player)commandSender).sendMessage(ChatColor.RED + "*ERROR* Usage: " + ChatColor.GRAY + "/tbcm-dc create [Name] L: [X] [Y] [Z]");
                    }
                }
            }
        }

        return true;
    }
}
