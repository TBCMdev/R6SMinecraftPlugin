package me.R6SMC.plugin.GameLogic;

import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;

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
        GameLogic.world.setGameRule(GameRule.DO_FIRE_TICK,false);
        GameChat.BroadcastMessage("your game is starting. please pick a team to be on and an operator to play." +
                " the game will start when ALL players in the server have picked a team AND an operator", ChatColor.DARK_GREEN);
        Main.GameStart();
    }
}
