package me.R6SMC.plugin.GameLogic;

import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.PlayerLogic.PlayerClass;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class Podium {

    private static float PodiumYAW = 0,PodiumPITCH = 0;
    public static boolean SetPodium(List<Location> PodiumPositions){
        HashMap<Integer, PlayerClass> FinishingPoints = new HashMap<>();
        for(PlayerClass pc : GameChat.GetAllPlayersClasses()){
            int Points = pc.getPoints();
            FinishingPoints.put(Points,pc);
        }
        List<Integer> points = new ArrayList<>(FinishingPoints.keySet());
        Collections.sort(points);
        List<String> FinishingNames = new ArrayList<>();
        for(PlayerClass p : FinishingPoints.values()){
            FinishingNames.add(p.getPlayer().getDisplayName());
        }
        String FinishedResults = FinishedScores.GetEndResults(FinishingNames);
        for(Player pl : GameChat.GetAllPlayers()){
            pl.sendMessage(FinishedResults);
        }
        try{
            PlayerClass TopPlayer = FinishingPoints.get(points.get(0));
            Player TopPlayerOrigin = TopPlayer.getPlayer();
            //https://www.spigotmc.org/threads/creating-player-entities-with-skins.373306/
            //TopPlayerOrigin.teleport(new Location(GameLogic.world,PodiumPositions.get(0).getBlockX(),PodiumPositions.get(0).getBlockY(),PodiumPositions.get(0).getBlockZ(),PodiumYAW,PodiumPITCH));
            GameLogic.PlayersCanMove = false;
            TopPlayerOrigin.sendMessage(ChatColor.GREEN + "You got the most points in the game! well done! here are the results:");
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
