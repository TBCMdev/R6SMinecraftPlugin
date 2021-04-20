package me.R6SMC.plugin.PlayerLogic;

import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Sounds.Sounds;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerClass {
    public boolean DokkaebiCallDenied = false;
    public boolean DokkaebiCallRecieved = false;
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
    public void dokkaebiCall(){
        DokkaebiCallRecieved = true;
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!DokkaebiCallDenied){
                    Sounds.PlaySound(Sound.BLOCK_ANVIL_LAND,Player);
                    GameChat.sendActionbar(Player,ChatColor.DARK_RED + "PRESS AND HOLD THE DOKKAEBI ICON IN YOUR HOTBAR TO REMOVE THE EFFECT");
                }else{
                    DokkaebiCallDenied = false;
                    DokkaebiCallRecieved = false;
                    GameChat.sendActionbar(Player,ChatColor.GREEN + "You Deactivated Dokkaebi's spam call!");
                    cancel();
                }
            }
        }.runTaskTimer(GameLogic.mainThread,0,10);
    }
}
