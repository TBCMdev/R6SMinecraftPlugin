package me.R6SMC.plugin.Loadouts;

import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Ash;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Doc;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorHolder;
import me.R6SMC.plugin.PlayerLogic.Organiser;
import me.R6SMC.plugin.PlayerLogic.PlayerClass;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
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
    public static String[] LoadoutAruni =
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
                List<PlayerClass> PlayerOperatorValues = new ArrayList<>(PlayerOperators.values());
                Player CurrentPlayer = PlayerOperatorValues.get(i).getPlayer();

                if (PlayerOperators.containsKey(CurrentPlayer.getDisplayName())) {
                    switch (CheckForClass(PlayerOperators.get(CurrentPlayer.getDisplayName()))) {
                        case 1:
                            Bukkit.getLogger().info(CurrentPlayer.getDisplayName());
                            if (CurrentPlayer != null) {
                                if(CurrentPlayer.getGameMode() != GameMode.SPECTATOR) {
                                    for (String cmmd : Loadoutdoc) {
                                        if (cmmd != null) {
                                            Bukkit.getLogger().info(cmmd);
                                            Bukkit.getLogger().info(CurrentPlayer.getDisplayName());
                                            CurrentPlayer.performCommand(cmmd);
                                            //Organiser.Organise(CurrentPlayer, DevConsole.KEEPAMMO);
                                        }
                                    }
                                }else{
                                    DevConsole.SendDevMessage(CurrentPlayer,"you are in spectator.",DevConsole.TESTING);
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
                                        //Organiser.Organise(CurrentPlayer,DevConsole.KEEPAMMO);
                                    }
                                }
                            }
                            break;
                        case 3:
                            Bukkit.getLogger().info(CurrentPlayer.getDisplayName());
                            if (CurrentPlayer != null) {
                                for (String cmmd : LoadoutAruni) {
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
                }else{
                    DevConsole.SendDevMessage(CurrentPlayer,"Could not give you your loadout! ERROR",DevConsole.TESTING);
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
            case "Aruni":
                return 3;
            case "ash":
                try {
                    new Ash(OperatorHolder.GetOperator(player.getPlayer()));
                }catch (Exception e){
                    DevConsole.SendDevMessage(player.getPlayer(),"could not instantiate Ash class.",DevConsole.TESTING);
                }
                return 4;
            case "capitao":
                return 5;
            case "finka":
                return 6;
            default:
                return 0;
        }
    }
    public static boolean GiveAbilityItem(int Operator, Player p){
        switch (Operator){
            case 1:
                p.getInventory().addItem(GameLogic.docAbil);
                break;
            case 2:
                p.getInventory().addItem(GameLogic.rookAbil);
                break;
            case 3:
                p.getInventory().addItem(GameLogic.AruniAbil);
                break;
            case 4:
                p.getInventory().addItem(GameLogic.ashAbil);
                break;
            case 5:
                p.getInventory().addItem(GameLogic.finkaAbil);
                break;
            case 6:
                p.getInventory().addItem(GameLogic.capitaoAbil);
                break;
        }
        return true;
    }
}
