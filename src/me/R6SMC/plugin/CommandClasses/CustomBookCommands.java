package me.R6SMC.plugin.CommandClasses;

import me.R6SMC.plugin.Books.CustomBooks;
import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.PlayerLogic.PlayerClass;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class CustomBookCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        GameLogic.ShowAvailableOperators();
        Player p = (Player) sender;
        if(GameLogic.PlayerClasses.containsKey(p.getDisplayName())) {
            PlayerClass playerClass = GameLogic.PlayerClasses.get(p.getDisplayName());
            if(playerClass.GetTeam() == 1) {
                if (args.length > 0) {
                    switch (args[0]) {
                        case "doc":
                            if (args[1].equals("2") && CustomBooks.CheckForValidPage(0,1))
                            {
                                ItemStack BlueBook = p.getInventory().getItemInMainHand();
                                BookMeta BlueBookMeta = (BookMeta) BlueBook.getItemMeta();
                                BlueBookMeta.spigot().setPages(CustomBooks.GetPage(0, 1));
                                DevConsole.SendDevMessage(p,"Page is valid and setting pages.",DevConsole.TESTING);
                                break;
                            }
                            else
                            {
                                GameChat.SendMessage(ChatColor.RED + "*ERROR* " + ChatColor.GRAY + " Command argument 2 does not reach any requirements in switch block. error in func:" + CustomBooks.CheckForValidPage(0,1), p);
                            }
                            break;
                        case "rook":
                            if (args[1].equals("3") && CustomBooks.CheckForValidPage(1,1))
                            {
                                ItemStack BlueBook = p.getInventory().getItemInMainHand();
                                BookMeta BlueBookMeta = (BookMeta) BlueBook.getItemMeta();
                                BlueBookMeta.spigot().setPages(CustomBooks.GetPage(1, 1));
                                DevConsole.SendDevMessage(p,"Page is valid and setting pages.",DevConsole.TESTING);
                                break;
                            }
                            else
                            {
                                GameChat.SendMessage(ChatColor.RED + "*ERROR* " + ChatColor.GRAY + " Command argument 2 does not reach any requirements in switch block. error in func:" + CustomBooks.CheckForValidPage(0,1), p);
                            }
                            break;
                        case "tachanka":
                            if (args[1].equals("4") && CustomBooks.CheckForValidPage(2,1))
                            {
                                ItemStack BlueBook = p.getInventory().getItemInMainHand();
                                BookMeta BlueBookMeta = (BookMeta) BlueBook.getItemMeta();
                                BlueBookMeta.spigot().setPages(CustomBooks.GetPage(2, 1));
                                DevConsole.SendDevMessage(p,"Page is valid and setting pages.",DevConsole.TESTING);
                                break;
                            }
                            else
                            {
                                GameChat.SendMessage(ChatColor.RED + "*ERROR* " + ChatColor.GRAY + " Command argument 2 does not reach any requirements in switch block.", p);
                            }
                            break;
                        default:
                            GameChat.SendMessage(ChatColor.RED + "*ERROR* " + ChatColor.GRAY + " Command argument 1 does not reach any requirements in switch block.", p);
                            break;
                    }
                }
            }
        }


        return true;
    }
}
