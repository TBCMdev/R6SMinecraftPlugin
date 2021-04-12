package me.R6SMC.plugin;

import com.sun.istack.internal.NotNull;
import me.R6SMC.plugin.Operators.OperatorHandler;
import me.R6SMC.plugin.Operators.OperatorHolder;
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
                sender.sendMessage(ChatColor.RED + "*ERROR* " + ChatColor.GRAY + "usage: /defoperator [doc / rook / tachanka]");
                return true;
            }
            if(!args[0].equalsIgnoreCase("doc") && !args[0].equalsIgnoreCase("rook") && !args[0].equalsIgnoreCase("tachanka")) {
                sender.sendMessage(ChatColor.RED + "*ERROR* " + ChatColor.GRAY + "usage: /defoperator [doc / rook / tachanka]");
            }
            if(args[0].equalsIgnoreCase("doc")) {
                Bukkit.getLogger().info("picking operator...");
                CheckCommand(sender,args,"doc");
            }
            if(args[0].equalsIgnoreCase("rook")) {
                Bukkit.getLogger().info("picking operator...");
                CheckCommand(sender,args,"rook");
            }
            if(args[0].equalsIgnoreCase("tachanka")) {
                Bukkit.getLogger().info("picking operator...");
                CheckCommand(sender,args,"tachanka");
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
                GameLogic.ash = sender.getName().toString();
                if(!Main.PlayersWOP.contains(sender.getName().toString())) {
                    sender.sendMessage(ChatColor.GREEN + "you have chosen ash! to use her abilities, please refer to the action bar above your hotbar, when teleported into the game");
                    GameLogic.PickedOperators ++;
                    Bukkit.getLogger().info("operator picked");
                    Main.PlayersWOP.add(sender.getName().toString());
                }else {
                    sender.sendMessage(ChatColor.RED + "you have allready chosen an operator!");
                }
                GameLogic.ResendTeleportRed();
                GameLogic.ResendTeleportBlue();
            }
            if(args[0].equalsIgnoreCase("capitao")) {
                GameLogic.capitao = sender.getName().toString();
                if(!Main.PlayersWOP.contains(sender.getName().toString())) {
                    sender.sendMessage(ChatColor.GREEN + "you have chosen ash! to use her abilities, please refer to the action bar above your hotbar, when teleported into the game");
                    GameLogic.PickedOperators ++;
                    Bukkit.getLogger().info("operator picked");
                    Main.PlayersWOP.add(sender.getName().toString());
                }else {
                    sender.sendMessage(ChatColor.RED + "you have allready chosen an operator!");
                }
                GameLogic.ResendTeleportRed();
                GameLogic.ResendTeleportBlue();
            }
            if(args[0].equalsIgnoreCase("finka")) {
                GameLogic.finka = sender.getName().toString();
                if(!Main.PlayersWOP.contains(sender.getName().toString())) {
                    CheckCommand((Player)sender,args,"finka");
                    sender.sendMessage(ChatColor.GREEN + "you have chosen finka! to use her abilities, please refer to the action bar above your hotbar, when teleported into the game");
                    GameChat.sendActionbar((Player) sender,"to use finkas ability, please hold the ability item in your hotbar and click!");
                    GameLogic.PickedOperators ++;
                    Bukkit.getLogger().info("operator picked");
                    Main.PlayersWOP.add(sender.getName().toString());
                }else {
                    sender.sendMessage(ChatColor.RED + "you have allready chosen an operator!");
                }
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
                GameLogic.JoinTeamRed((Player) sender);
            }else{
                sender.sendMessage(ChatColor.RED + "*ERROR*" + ChatColor.BOLD + ChatColor.GRAY + " Please join the opposing team, to make the teams equal");
            }

        }
        if(cmd.getName().equalsIgnoreCase("jointeamblue")) {
            if(GameLogic.RedTeamCount > GameLogic.BlueTeamCount || GameLogic.RedTeamCount == 0 && GameLogic.BlueTeamCount == 0) {
                GameLogic.JoinTeamBlue((Player) sender);
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
