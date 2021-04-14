package me.R6SMC.plugin.GameLogic;

import me.R6SMC.plugin.Chat.GameChat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GameRounds {

    public static int MaxRounds;
    public static int OvertimeRounds;
    public static int CurrentRound;
    public static int BlueTeamRounds = 0,RedTeamRounds = 0;
    public static boolean IsOvertime = false;
    public static boolean GameFinished = false;
    public static boolean NewRound(){
        try{
            if(CheckToContinueGame()){
                CreateNewRound();
            }
            return true;
        }catch (Exception e){
            return false;
        }
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
        }
        return false;
    }
    public static void endGame(){
    }
    public static void endRound(int WinningTeam){
        if(!GameFinished) {
            if (WinningTeam == 1) {
                //RED
                RedTeamRounds++;
                CheckToContinueGame();
            } else if (WinningTeam == 2) {
                BlueTeamRounds++;
                CheckToContinueGame();
            }
        }
    }
    public static void CreateNewRound(){

    }
    public static void NewGame(){

    }
    public static void resetRounds(){

    }
}
