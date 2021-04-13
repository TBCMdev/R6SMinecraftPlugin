package me.R6SMC.plugin.PlayerLogic;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerClass {
    public static Player Player;
    public static String Name;
    public static String Class;
    public static int ID;
    public static int Points;
    public static int Team;
    public PlayerClass(Player p,String n, String C,int id,int t){
        Player = p;
        Name = n;
        Class = C;
        ID = id;
        Team = t;
    }
    public static int GetTeam(){
        return Team;
    }
    public static String PlayerName(){
        return Player.getDisplayName();
    }
    public static int PlayerID(){
        return ID;
    }
    public void SetPoints(int points){
        Points += points;
        switch (Team){
            case 1:
                Player.sendMessage(ChatColor.RED + "+" + points);
                break;
            case 2:
                Player.sendMessage(ChatColor.BLUE + "+" + points);

        }
    }
    public Player getPlayer(){
        return this.Player;
    }
}
