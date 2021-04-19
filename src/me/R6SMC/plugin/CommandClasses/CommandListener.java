package me.R6SMC.plugin.CommandClasses;

import me.R6SMC.plugin.*;
import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.DevConsole.Overrider;
import me.R6SMC.plugin.GameLogic.GameInitializer;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor {
    public static void CheckCommand(CommandSender sender,String[] args,String CheckString){
        if(args[0].equalsIgnoreCase(CheckString)){
            if(GameChat.GetAllPlayers().size() <= 0) return;
            if(GameChat.GetAllPlayers().contains((Player) sender)) {

                OperatorHandler.PickOperator(CheckString,sender);
            }else{
                sender.sendMessage(ChatColor.RED + "you have already chosen an operator!");
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(cmd.getName().equalsIgnoreCase("defoperator")) {

            if(args.length == 0) {
                sender.sendMessage(ChatColor.RED + "*ERROR* " + ChatColor.GRAY + "usage: /defoperator [doc / rook / Aruni]");
                return true;
            }
            if(!args[0].equalsIgnoreCase("doc") && !args[0].equalsIgnoreCase("rook") && !args[0].equalsIgnoreCase("Aruni")) {
                sender.sendMessage(ChatColor.RED + "*ERROR* " + ChatColor.GRAY + "usage: /defoperator [doc / rook / Aruni]");
            }
            if(args[0].equalsIgnoreCase("doc")) {
                Bukkit.getLogger().info("picking operator...");
                CheckCommand(sender,args,"doc");
            }
            if(args[0].equalsIgnoreCase("rook")) {
                Bukkit.getLogger().info("picking operator...");
                CheckCommand(sender,args,"rook");
            }
            if(args[0].equalsIgnoreCase("Aruni")) {
                Bukkit.getLogger().info("picking operator...");
                CheckCommand(sender,args,"Aruni");
            }
        }
        if(cmd.getName().equalsIgnoreCase("attkoperator")) {



            if(args.length == 0) {
                sender.sendMessage(ChatColor.RED + "*ERROR* " + ChatColor.BOLD + ChatColor.GRAY + "usage: /attkoperator [ash / capitao / finka]");
                return true;
            }
            if(!args[0].equalsIgnoreCase("ash") && !args[0].equalsIgnoreCase("capitao") && !args[0].equalsIgnoreCase("finka")) {
                sender.sendMessage(ChatColor.RED + "*ERROR* " + ChatColor.BOLD + ChatColor.GRAY + "usage: /attkoperator [ash / capitao / finka]");
            }
            if(args[0].equalsIgnoreCase("ash")) {
                Bukkit.getLogger().info("picking operator...");
                CheckCommand(sender,args,"ash");
                GameLogic.ResendTeleportRed();
                GameLogic.ResendTeleportBlue();
            }
            if(args[0].equalsIgnoreCase("capitao")) {
                Bukkit.getLogger().info("picking operator...");
                CheckCommand(sender,args,"capitao");
                GameLogic.ResendTeleportRed();
                GameLogic.ResendTeleportBlue();
            }
            if(args[0].equalsIgnoreCase("finka")) {
                Bukkit.getLogger().info("picking operator...");
                CheckCommand(sender,args,"finka");
                GameLogic.ResendTeleportRed();
                GameLogic.ResendTeleportBlue();

            }




        }
        if(cmd.getName().equalsIgnoreCase("override")){
            if(args[0].equalsIgnoreCase("movement")){
                if(args[1] != null){
                    if(args[1].equalsIgnoreCase("true")){
                        Overrider.OverridePlayerMovement(true,(Player) sender);
                    }else if(args[1].equalsIgnoreCase("false")){
                        Overrider.OverridePlayerMovement(false,(Player) sender);
                    }
                }
            }
        }
        if(cmd.getName().equalsIgnoreCase("jointeamred")) {
            if(GameLogic.RedTeamCount < GameLogic.BlueTeamCount || GameLogic.RedTeamCount == 0 && GameLogic.BlueTeamCount == 0) {
                GameLogic.JoinTeam(2,(Player) sender);
            }else{
                sender.sendMessage(ChatColor.RED + "*ERROR*" + ChatColor.BOLD + ChatColor.GRAY + " Please join the opposing team, to make the teams equal");
            }

        }
        if(cmd.getName().equalsIgnoreCase("jointeamblue")) {
            if(GameLogic.RedTeamCount > GameLogic.BlueTeamCount || GameLogic.RedTeamCount == 0 && GameLogic.BlueTeamCount == 0) {
                GameLogic.JoinTeam(1,(Player) sender);
            }else{
                sender.sendMessage(ChatColor.RED + "*ERROR*" + ChatColor.BOLD + ChatColor.GRAY + " Please join the opposing team, to make the teams equal");
            }

        }
        if(cmd.getName().equalsIgnoreCase("ability")){
            if(args[0].equalsIgnoreCase("doc")) {
                Player p = (Player) sender;
                p.getInventory().addItem(GameLogic.docAbil);
            }else {
                sender.sendMessage("this command is in maintenance and has limited use");
            }
        }
        if(cmd.getName().equalsIgnoreCase("start")){
            GameInitializer.Start();
        }
        return true;
    }
}
