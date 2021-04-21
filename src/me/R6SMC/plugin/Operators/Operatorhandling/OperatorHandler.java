package me.R6SMC.plugin.Operators.Operatorhandling;

import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class OperatorHandler {



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
                    if(!CurrentOperators.CurrentOperators.containsKey(player)){
                        CurrentOperators.Add(player,1);
                    }

                    break;
                case "rook":
                    GameLogic.rook = sender.getName().toString();
                    Main.PlayerOperators.put(GameLogic.rook,(Player) sender);
                    GameLogic.PickedOperators ++;
                    sender.sendMessage(ChatColor.GREEN + "you have chosen rook! to use his abilities, please refer to the action bar above your hotbar, when teleported into the game");
                    GameLogic.CreatePlayerClass(player, player.getDisplayName(),"rook",GameLogic.PickedOperators,1);
                    getLogger().info("operator picked");
                    Main.PlayersWOP.add(sender.getName().toString());
                    if(!CurrentOperators.CurrentOperators.containsKey(player)){
                        CurrentOperators.Add(player,2);
                    }
                    break;
                case "aruni":
                    GameLogic.Aruni = sender.getName().toString();
                    Main.PlayerOperators.put(GameLogic.Aruni,(Player) sender);
                    GameLogic.PickedOperators ++;
                    sender.sendMessage(ChatColor.GREEN + "you have chosen Aruni! to use his abilities, please refer to the action bar above your hotbar, when teleported into the game");
                    GameLogic.CreatePlayerClass(player, player.getDisplayName(),"aruni",GameLogic.PickedOperators,1);
                    getLogger().info("operator picked");
                    Main.PlayersWOP.add(sender.getName().toString());
                    if(!CurrentOperators.CurrentOperators.containsKey(player)){
                        CurrentOperators.Add(player,3);
                    }
                    break;
                case "ash":
                    GameLogic.ash = sender.getName();
                    Main.PlayerOperators.put(GameLogic.ash,(Player) sender);
                    GameLogic.PickedOperators++;
                    sender.sendMessage(ChatColor.GREEN + "you have chosen ash! to use her abilities, please refer to the action bar above your hotbar, when teleported into the game");
                    GameLogic.CreatePlayerClass(player, player.getDisplayName(),"ash",GameLogic.PickedOperators,2);
                    getLogger().info("operator picked");
                    Main.PlayersWOP.add(sender.getName().toString());
                    if(!CurrentOperators.CurrentOperators.containsKey(player)){
                        CurrentOperators.Add(player,4);
                    }
                case "finka":
                    GameLogic.finka = sender.getName();
                    Main.PlayerOperators.put(GameLogic.finka,(Player) sender);
                    GameLogic.PickedOperators++;
                    sender.sendMessage(ChatColor.GREEN + "you have chosen finka! to use her abilities, please refer to the action bar above your hotbar, when teleported into the game");
                    GameLogic.CreatePlayerClass(player, player.getDisplayName(),"finka",GameLogic.PickedOperators,2);
                    getLogger().info("operator picked");
                    Main.PlayersWOP.add(sender.getName().toString());
                    if(!CurrentOperators.CurrentOperators.containsKey(player)){
                        CurrentOperators.Add(player,5);
                    }
                default:
                    sender.sendMessage(ChatColor.RED + "*ERROR* " + ChatColor.GRAY + " usage: /defoperator [doc / rook / Aruni]");
                    break;

            }
            GameLogic.ResendTeleportRed();
            GameLogic.ResendTeleportBlue();
        }

}
