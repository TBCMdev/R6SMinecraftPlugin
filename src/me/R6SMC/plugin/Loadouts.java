package me.R6SMC.plugin;

import me.R6SMC.plugin.Operators.Doc;
import me.R6SMC.plugin.Operators.OperatorHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;

public class Loadouts {
    public static String[] Loadoutdoc =
            {
                    "function give:mp5",
                    "function give:glock17"
            };
    public static String[] LoadoutRook =
            {
                    "function give:mp5",
                    "function give:desert_eagle"
            };
    public static String[] LoadoutTachanka =
            {
                    "function give:m500",
                    "function give:mac10"
            };
    public static String[] LoadoutAsh =
            {
                    "function give:m4",
                    "function give:glock18"
            };
    public static String[] LoadoutCapitao =
            {
                    "function give:g3",
                    "function give:glock18"
            };
    public static String[] LoadoutFinka =
            {
                    "function give:m16",
                    "function give:makarov"
            };

    public static void GiveLoadouts(Map<String, PlayerClass> PlayerOperators) {
        for (int i = 0; i < PlayerOperators.size();i++) {
            Bukkit.getLogger().info("looping...");
                Bukkit.getLogger().info("SETTING LOADOUTS");
                Player CurrentPlayer = PlayerOperators.get(i).Player;
                if (GameLogic.PlayerClasses.containsKey(CurrentPlayer.getDisplayName())) {
                    switch (CheckForClass(GameLogic.PlayerClasses.get(CurrentPlayer.getDisplayName()))) {
                        case 1:
                            Bukkit.getLogger().info(CurrentPlayer.getDisplayName());
                            if (CurrentPlayer != null) {
                                for (String cmmd : Loadoutdoc) {
                                    if (cmmd != null) {
                                        Bukkit.getLogger().info(cmmd);
                                        CurrentPlayer.performCommand(cmmd);
                                        Organiser.Organise(CurrentPlayer,DevConsole.KEEPAMMO);
                                    }
                                }
                            }
                            break;
                        case 2:
                            Bukkit.getLogger().info(CurrentPlayer.getDisplayName());
                            if (CurrentPlayer != null) {
                                for (String cmmd : LoadoutRook) {
                                    if (cmmd != null) {
                                        Bukkit.getLogger().info(cmmd);
                                        CurrentPlayer.performCommand(cmmd);
                                        Organiser.Organise(CurrentPlayer,DevConsole.KEEPAMMO);
                                    }
                                }
                            }
                            break;
                        case 3:
                            Bukkit.getLogger().info(CurrentPlayer.getDisplayName());
                            if (CurrentPlayer != null) {
                                for (String cmmd : LoadoutTachanka) {
                                    if (cmmd != null) {
                                        Bukkit.getLogger().info(cmmd);
                                        CurrentPlayer.performCommand(cmmd);
                                        Organiser.Organise(CurrentPlayer,DevConsole.KEEPAMMO);
                                    }
                                }
                            }
                            break;
                        case 4:
                            Bukkit.getLogger().info(CurrentPlayer.getDisplayName());
                            if (CurrentPlayer != null) {
                                for (String cmmd : LoadoutAsh) {
                                    if (cmmd != null) {
                                        Bukkit.getLogger().info(cmmd);
                                        CurrentPlayer.performCommand(cmmd);
                                        Organiser.Organise(CurrentPlayer,DevConsole.KEEPAMMO);
                                    }
                                }
                            }
                            break;
                        case 5:
                            Bukkit.getLogger().info(CurrentPlayer.getDisplayName());
                            if (CurrentPlayer != null) {
                                for (String cmmd : LoadoutCapitao) {
                                    if (cmmd != null) {
                                        Bukkit.getLogger().info(cmmd);
                                        CurrentPlayer.performCommand(cmmd);
                                        Organiser.Organise(CurrentPlayer,DevConsole.KEEPAMMO);
                                    }
                                }
                            }
                            break;
                        case 6:
                            Bukkit.getLogger().info(CurrentPlayer.getDisplayName());
                            if (CurrentPlayer != null) {
                                for (String cmmd : LoadoutFinka) {
                                    if (cmmd != null) {
                                        Bukkit.getLogger().info(cmmd);
                                        CurrentPlayer.performCommand(cmmd);
                                        Organiser.Organise(CurrentPlayer,DevConsole.KEEPAMMO);
                                    }
                                }
                            }
                            break;

                    }
                }

        }
    }


//CREATES STATIC CLASSES OF OPERATORS HERE
    public static int CheckForClass(PlayerClass player) {
        switch (player.Class) {
            case "doc":
                //JUST TESTING HERE
                try {
                    new Doc(OperatorHolder.GetOperator(player.getPlayer()));
                }catch (Exception e){
                    DevConsole.SendDevMessage(player.getPlayer(),"could not instantiate Doc class.",DevConsole.TESTING);
                }
                return 1;
            case "rook":
                return 2;
            case "tachanka":
                return 3;
            case "ash":
                return 4;
            case "capitao":
                return 5;
            case "finka":
                return 6;
            default:
                return 0;
        }
    }
}
