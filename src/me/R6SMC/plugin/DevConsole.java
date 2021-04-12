package me.R6SMC.plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
    public static boolean GetTester(){
        return TESTING;
    }
    public static String GetDevPrefix(){
        return DevPrefix;
    }
    public static String GetDevTestingPrefix(){
        return DevTestingPrefix;
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
            }
        }

        return true;
    }
}
