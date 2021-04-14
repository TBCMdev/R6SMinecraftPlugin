package me.R6SMC.plugin.PlayerLogic;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerClass {
    public Player Player;
    public String Name;
    public String Class;
    public int ID;
    public int Points;
    public int Team;
    public PlayerClass(Player p,String n, String C,int id,int t){
        Player = p;
        Name = n;
        Class = C;
        ID = id;
        Team = t;
    }
    public int getPoints(){return Points;}
    public int GetTeam(){
        return Team;
    }
    public String PlayerName(){
        return Player.getDisplayName();
    }
    public int PlayerID(){
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
