package me.R6SMC.plugin;

import org.bukkit.ChatColor;

public class GameInitializer {


    public static void Start(){
        GameLogic.RedTeam.clear();
        GameLogic.BlueTeam.clear();
        GameLogic.ash = "";
        GameLogic.capitao = "";
        GameLogic.finka = "";
        GameLogic.doc = "";
        GameLogic.rook = "";
        GameLogic.tachanka = "";
        GameLogic.PositionList.clear();
        GameLogic.joinedPlayers = 0;
        GameLogic.players = 0;
        GameLogic.PickedOperators = 0;
        GameLogic.RedTeamCount = 0;
        GameLogic.counterA = 0;
        GameLogic.BlueTeamCount = 0;
        GameChat.BroadcastMessage("your game is starting. please pick a team to be on and an operator to play." +
                " the game will start when ALL players in the server have picked a team AND an operator", ChatColor.DARK_GREEN);
        Main.GameStart();
    }
}
