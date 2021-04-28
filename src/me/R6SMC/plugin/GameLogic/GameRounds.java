package me.R6SMC.plugin.GameLogic;

import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.Operators.Operatorhandling.CurrentOperators;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorHandler;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorHolder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameRounds {

    public static int MaxRounds;
    public static int OvertimeRounds;
    public static int CurrentRound = 1;
    public static int BlueTeamRounds = 0,RedTeamRounds = 0;
    public static boolean IsOvertime = false;
    public static boolean GameFinished = false;
    public static boolean GameShouldContinue = false;
    public static boolean getShouldContinue(){
        return GameShouldContinue;
    }
    public static boolean NewRound(int winningTeam){
        try{
            CreateNewRound(winningTeam);
            return true;

        }catch (Exception e){
            return false;
        }
    }
    public static int getCurrentRound(){
        return CurrentRound;
    }
    public static boolean CheckToContinueGame(){
        if(BlueTeamRounds + RedTeamRounds == 6){
            IsOvertime = true;
            //CHECKING TO NOTIFY PLAYERS THAT IT IS AN OVERTIME MATCH
            for(Player p : GameChat.GetAllPlayers()){
                p.sendMessage(ChatColor.GOLD + "YOU ARE NOW INTO OVERTIME!! Good luck...");
            }
            return true;
        }else if(BlueTeamRounds >= 4 && !IsOvertime || RedTeamRounds >= 4 && !IsOvertime){
        //END GAME
            GameShouldContinue = false;
            endGame();
            return false;
        }else if(RedTeamRounds > BlueTeamRounds && RedTeamRounds == 3){
            //CHECKING FOR RED TEAM MATCH POINT
            for(Player p : GameLogic.BlueTeam){
                p.sendMessage(ChatColor.DARK_RED  + "The Red Team is on match point! you've got this!");
            }
            return true;
        }else if(RedTeamRounds < BlueTeamRounds && BlueTeamRounds == 3){
            //CHECKING FOR BLUE TEAM MATCH POINT
            for(Player p : GameLogic.RedTeam){
                p.sendMessage(ChatColor.DARK_RED  + "The Blue Team is on match point! you've got this!");
            }
            return true;
        }else if(IsOvertime){
            if(RedTeamRounds == 4 && BlueTeamRounds == 4){
                for(Player p : GameChat.GetAllPlayers()){
                    p.sendMessage(ChatColor.GOLD + "THIS IS THE LAST ROUND, THE WINNING TEAM WINS THE GAME! GOOD LUCK.");
                }
            }
            return true;
        }else if (RedTeamRounds < 4 && BlueTeamRounds < 4 && !IsOvertime){
            for(Player p : GameChat.GetAllPlayers()){
                p.sendMessage(ChatColor.RED + " CURRENT ROUND: "+ChatColor.WHITE+"ROUND " + CurrentRound);
            }
            return true;
        }
        return false;
    }
    public static void endGame(){
    }
    public static void endRound(int WinningTeam){
        if(!GameFinished) {
            CurrentRound ++;
            if (WinningTeam == 1) {
                //RED
                RedTeamRounds++;
            } else if (WinningTeam == 2) {
                BlueTeamRounds++;
            }
        }
    }
    public static void CreateNewRound(int winningTeam) {
        endRound(winningTeam);
        CurrentOperators.ResetOperators();
        if (CheckToContinueGame()) {
            for (Player p : GameChat.GetAllPlayers()) {
                p.getInventory().clear();
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    GameLogic.TeleportPlayersPickingOPS();
                    GameLogic.GiveWindowsAfterInit();
                }
            }.runTaskLater(GameLogic.mainThread, 100);
        }
    }
    public static void NewGame(){

    }
    public static void resetRounds(){

    }
}
