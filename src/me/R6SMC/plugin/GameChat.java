package me.R6SMC.plugin;


import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.chat.TextComponent;

import java.lang.reflect.Array;
import java.util.*;

public class GameChat {
    public static void SendMessage(String Message, Player p){
        p.sendMessage(Message);
    }
    public static void SendSpigotMessage(TextComponent Message,Player p){
        p.spigot().sendMessage(Message);
    }
    public static void SendMessage(String Message, ChatColor color, Player p){
        p.sendMessage(color + Message);
    }
    public static void SendMessage(String Message, Player[] players){
        for(Player p : players){
            p.sendMessage(Message);
        }
    }
    public static void SendMessage(String Message, ChatColor color, Player[] players){
        for(Player p : players){
            p.sendMessage(color + Message);
        }
    }
    public static void BroadcastMessage(String Message){
        Bukkit.getServer().broadcastMessage(Message);
    }
    public static void BroadcastMessage(String Message, ChatColor color){
        Bukkit.getServer().broadcastMessage(color + Message);
    }
    public static void PerformCustomCommand(String Command, Player p){
        p.performCommand(Command);
    }
    public static void BroadcastCustomCommand(String Command){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.performCommand(Command);
        }
    }
    public static TextComponent CreateTextComponent(String Content){
        return new TextComponent(Content);
    }
    public static TextComponent CreateTextComponent(String Content,ChatColor color){
        return new TextComponent(color + Content);
    }
    public static TextComponent[] CreateTextComponents(TextComponent[] messages,String[] messageContents,ChatColor color){
        for(int i = 0; i < messages.length; i ++){
            messages[i] = new TextComponent(color + messageContents[i]);
        }
        return messages;
    }
    public static void SendAllTextEvents(TextComponent[] texts,Player p){
        for(TextComponent t : texts){
            p.spigot().sendMessage(t);
        }
    }
    public static TextComponent CreateHoverEvent(HoverEvent.Action action,TextComponent message,String HoverText){
        message.setHoverEvent(new HoverEvent(action, new Text(HoverText)));
        return message;
    }
    public static TextComponent[] CreateHoverEvents(HoverEvent.Action action,TextComponent[] messages,String[] HoverTexts){
        for(int i = 0; i < messages.length;i++) {
            messages[i].setHoverEvent(new HoverEvent(action, new Text(HoverTexts[i])));
        }
        return messages;
    }
    public static TextComponent CreateClickEvent(ClickEvent.Action action,TextComponent message,String Command){
        message.setClickEvent(new ClickEvent(action,Command));
        return message;
    }
    public static TextComponent[] CreateClickEvents(ClickEvent.Action action,TextComponent[] messages,String[] ClickTexts){
        for(int i = 0; i < messages.length;i++) {
            messages[i].setClickEvent(new ClickEvent(action,ClickTexts[i]));
        }
        return messages;
    }
    public static void sendActionbar(Player p, String bar) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(bar));
    }
    public static List<Player> GetAllPlayers(){
        List<Player> PlayersPlaying = new ArrayList<>();
        for(Player p : GameLogic.RedTeam){
            PlayersPlaying.add(p);
        }
        for(Player p : GameLogic.BlueTeam){
            PlayersPlaying.add(p);
        }
        if(PlayersPlaying.size() > 0) {
            return PlayersPlaying;
        }
        return null;

    }
    public static PlayerClass GetPlayerClass(Player p){
        return GameLogic.PlayerClasses.get(p.getDisplayName());
    }
    public static List<PlayerClass> GetAllPlayersClasses(){
        List<PlayerClass> PlayersPlaying = new ArrayList<PlayerClass>();
        for(Player p : GameLogic.RedTeam){
            PlayersPlaying.add(GameLogic.PlayerClasses.get(p.getDisplayName()));
        }
        for(Player p : GameLogic.BlueTeam){
            PlayersPlaying.add(GameLogic.PlayerClasses.get(p.getDisplayName()));
        }
        if(PlayersPlaying.size() > 0) {
            return PlayersPlaying;
        }
        return null;

    }
    public static ChatColor GetTeamColor(PlayerClass p){
        if(p.Team == 1){
            return ChatColor.RED;
        }
        if(p.Team == 2){
            return ChatColor.BLUE;
        }
        return null;
    }
    public static ComponentBuilder CreateCB(String Content,HoverEvent.Action e,String HoverEventText,ClickEvent.Action c,String Command){
        return new ComponentBuilder(Content).event(new HoverEvent(e,new Text(HoverEventText))).event(new ClickEvent(c,Command));
    }
}
