package me.R6SMC.plugin.PlayerLogic;

import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Sounds.Sounds;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.swing.plaf.synth.Region;

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
    //region DokkaebiCall
    private boolean IsTryingToRemoveCall = false;
    private int ResetCallCounter = 0;


    public void TryToRemoveDokkaebiCall(boolean isHolding){
        if(!IsTryingToRemoveCall) {
            IsTryingToRemoveCall = true;
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(IsTryingToRemoveCall) {
                        ResetCallCounter ++;
                        GameChat.sendActionbar(Player,ChatColor.DARK_GREEN + "" + (ResetCallCounter / 10) * 100 + "%");
                        if(ResetCallCounter >= 10) {
                            ResetCallCounter = 0;
                            DokkaebiCallDenied = true;
                            IsTryingToRemoveCall = false;
                            cancel();
                        }
                    }else{
                        ResetCallCounter = 0;
                        IsTryingToRemoveCall = false;
                        cancel();
                    }

                }
            }.runTaskTimer(GameLogic.mainThread,0,10);
        }
    }
    public void FailedToDisableCall(){
        IsTryingToRemoveCall = false;
    }
    public void dokkaebiCall(){
        DokkaebiCallRecieved = true;
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!DokkaebiCallDenied){
                    Sounds.PlaySound(Sound.BLOCK_ANVIL_LAND, Player);
                    if(!IsTryingToRemoveCall) {
                        GameChat.sendActionbar(Player, ChatColor.DARK_RED + "PRESS AND HOLD THE DOKKAEBI ICON IN YOUR HOTBAR TO REMOVE THE EFFECT");
                    }
                }else{
                    DokkaebiCallDenied = false;
                    DokkaebiCallRecieved = false;
                    GameChat.sendActionbar(Player,ChatColor.GREEN + "You Deactivated Dokkaebi's spam call!");
                    cancel();
                }
            }
        }.runTaskTimer(GameLogic.mainThread,0,10);
    }
    //endregion
}
