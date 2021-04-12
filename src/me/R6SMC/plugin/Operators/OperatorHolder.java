package me.R6SMC.plugin.Operators;

import me.R6SMC.plugin.DevConsole;
import me.R6SMC.plugin.GameChat;
import me.R6SMC.plugin.GameLogic;
import me.R6SMC.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static org.bukkit.Bukkit.getLogger;

public class OperatorHolder {
    public static HashMap<Player,OperatorUtility> operators = new HashMap<>();


    public static void PickOperator(String Name, CommandSender sender){
        Player player = (Player)sender;
        switch(Name.toLowerCase()) {
            case "doc":
                GameLogic.doc = sender.getName().toString();
                Main.PlayerOperators.put(GameLogic.doc,(Player) sender);
                GameLogic.PickedOperators ++;
                GameLogic.CreatePlayerClass(player, player.getDisplayName(),"doc",GameLogic.PickedOperators,1);
                getLogger().info("player who has picked doc:" + GameLogic.doc);
                sender.sendMessage(ChatColor.GREEN + "you have chosen doc! to use his abilities, please refer to the action bar above your hotbar, when teleported into the game");
                GameChat.sendActionbar((Player) sender, ChatColor.BLUE + "click to activate docs healing effect");
                getLogger().info("operator picked.");
                Main.PlayersWOP.add(sender.getName().toString());
                break;
            case "rook":
                GameLogic.rook = sender.getName().toString();
                Main.PlayerOperators.put(GameLogic.rook,(Player) sender);
                GameLogic.PickedOperators ++;
                sender.sendMessage(ChatColor.GREEN + "you have chosen rook! to use his abilities, please refer to the action bar above your hotbar, when teleported into the game");
                GameLogic.CreatePlayerClass(player, player.getDisplayName(),"rook",GameLogic.PickedOperators,1);
                getLogger().info("operator picked");
                Main.PlayersWOP.add(sender.getName().toString());
                break;
            case "tachanka":
                GameLogic.tachanka = sender.getName().toString();
                Main.PlayerOperators.put(GameLogic.tachanka,(Player) sender);
                GameLogic.PickedOperators ++;
                sender.sendMessage(ChatColor.GREEN + "you have chosen tachanka! to use his abilities, please refer to the action bar above your hotbar, when teleported into the game");
                GameLogic.CreatePlayerClass(player, player.getDisplayName(),"tachanka",GameLogic.PickedOperators,1);
                getLogger().info("operator picked");
                Main.PlayersWOP.add(sender.getName().toString());
                break;
            default:
                sender.sendMessage(ChatColor.RED + "*ERROR* " + ChatColor.GRAY + " usage: /defoperator [doc / rook / tachanka]");
                break;
        }
        GameLogic.ResendTeleportRed();
        GameLogic.ResendTeleportBlue();
    }
    public static OperatorUtility GetOperator(Player p){
        OperatorUtility operator;
        try{
            if(operators.containsKey(p)) {
                return operators.get(p);
            }else{
                operator = new OperatorUtility(p, GameLogic.PlayerClasses.get(p.getDisplayName()));

                operators.put(p,operator);
                return operator;
            }
        }
        catch (Exception e){
            DevConsole.SendDevMessage(p,"Could not retrieve operator",DevConsole.TESTING);
        }

        return null;

   }
}
