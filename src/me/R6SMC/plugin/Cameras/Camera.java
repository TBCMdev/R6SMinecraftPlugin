package me.R6SMC.plugin.Cameras;

import javafx.util.Pair;
import me.R6SMC.plugin.Errors;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.GameLogic.PlayerEntities;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Random;

public class Camera {
    private static HashMap<Integer, Pair<CameraUtility,Location>> AllCams = new HashMap<Integer,Pair<CameraUtility,Location>>(){{
      /*example*/put(0,new Pair<>(new CameraUtility(
                0,
                1,
                new Location(GameLogic.world,0,0,0)),
                new Location(GameLogic.world,0,0,0)));
    }};
    private static HashMap<Player,Integer> AllCamsUsing = new HashMap<>();
    private static HashMap<Player, CameraUtility> AllPlayerCams = new HashMap<>();
    public static void leaveCamera(Player p,int Cam_index){
        if(AllPlayerCams.containsKey(p) && AllCamsUsing.containsKey(p)){
            AllPlayerCams.remove(p);
            AllCamsUsing.remove(p);
            initiateLeaveCameraFeed(p,Cam_index,AllCams.get(Cam_index).getValue());
            initiateLeaveCameraFeed(p,Cam_index,PlayerEntities.getEntityLocation(p));
            RemoveTemplateNPC(p);
        }else{
            p.sendMessage(ChatColor.RED + Errors.getError(Camera.class,"*IN_DE"/*index doesnt exist.*/,27));

        }
    }
    public static void accessCamera(Player p,CameraUtility cu,int Camera){
        if(!AllPlayerCams.containsKey(p) && !AllCamsUsing.containsKey(p)){
            givePlayerInfo(p);
            AllCamsUsing.put(p,cu.getIndex());
            AllPlayerCams.put(p,cu);
            initiateJoinCameraFeed(p,Camera);
            CreateTemplateNPC(p);
        }else{
            p.sendMessage(ChatColor.RED + Errors.getError(Camera.class,"*IN_AE"/*index allready exists.*/,36));
        }
    }
    public static void initiateLeaveCameraFeed(Player p, int cam_index, Location PlayerPlaceHolderLoc){
        try {
            p.setGameMode(GameMode.ADVENTURE);
            p.teleport(PlayerPlaceHolderLoc);
        }catch (Exception e) {
            p.sendMessage(ChatColor.RED + Errors.getError(Camera.class,"*CF_L"/*camera_feed leave*/,51));
        }
    }
    public static void initiateJoinCameraFeed(Player p,int cam_index){
        try {
            p.setGameMode(GameMode.SPECTATOR);
            p.teleport(AllCams.get(cam_index).getValue().subtract(0,2,0));
        }catch (Exception e) {
            p.sendMessage(ChatColor.RED + Errors.getError(Camera.class,"*CF_A"/*camera_feed activate*/,59));
        }
    }
    public static Pair<CameraUtility,Location> GetRandomActiveCam(){
            int rand = new Random().nextInt(AllCams.size());
            return AllCams.get(rand);
    }
    public static HashMap<Integer,Pair<CameraUtility,Location>> GetAllCams(){
        return AllCams;
    }
    public static HashMap<Player,Integer> GetAllCamsUsing(){
        return AllCamsUsing;
    }
    public static HashMap<Player,CameraUtility> getAllPlayerCamsUsing(){
        return AllPlayerCams;
    }
    public static int getPlayerUsingCam(Player p){
        try{
            return AllCamsUsing.get(p);
        }catch (Exception e){
            p.sendMessage(ChatColor.RED + Errors.getError(Camera.class,"*NP_UC"/*no player using camera*/,79));
            return -1;
        }

    }
    public static void CreateTemplateNPC(Player p){
        try {
            PlayerEntities.CreateEntity(p, p.getLocation());
        }catch (Exception e){
            p.sendMessage(ChatColor.DARK_RED + Errors.getError(Camera.class,"*NPC_C"/*NPC Create*/,79));

        }
    }
    public static void RemoveTemplateNPC(Player p){
        try {
            PlayerEntities.removeNpc(p);
        }catch (Exception e){
            p.sendMessage(ChatColor.DARK_RED + Errors.getError(Camera.class,"*NPC_R"/*NPC Remove*/,87));
        }
    }
    public static void givePlayerInfo(Player p){
        p.sendMessage(ChatColor.GOLD +
                "you are on CAMERA: " + ChatColor.LIGHT_PURPLE + AllCamsUsing.get(p) + ChatColor.GOLD +"."+
                "press " + ChatColor.LIGHT_PURPLE + "Q" + ChatColor.GOLD + " to exit the cams, and " + ChatColor.LIGHT_PURPLE +
                "right/left click " + ChatColor.GOLD + "to cycle cameras.");
    }
}
