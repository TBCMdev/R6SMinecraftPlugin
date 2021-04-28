package me.R6SMC.plugin.Cameras;

import com.mojang.authlib.GameProfile;
import javafx.util.Pair;
import me.R6SMC.plugin.Errors;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.GameLogic.PlayerEntities;
import org.bukkit.*;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.*;

import com.mojang.authlib.properties.Property;

public class Camera {
    private static HashMap<Integer, Pair<CameraUtility,Location>> AllCams = new HashMap<Integer,Pair<CameraUtility,Location>>(){{
/*example*/put(0,new Pair<>(new CameraUtility(0, 1,60,0,0),
        new Location(GameLogic.world,710.5,6,-697.5)));

        put(1,new Pair<>(new CameraUtility(1, 1, 45, 135, 0),
        new Location(GameLogic.world,716.5,17,-627.5,135,0)));

        put(3,new Pair<>(new CameraUtility(3, 1, 45, 135, 0),
                new Location(GameLogic.world,708,17,-655,135,0)));

        put(4,new Pair<>(new CameraUtility(4, 1, 45, 135, 0),
                new Location(GameLogic.world, 727,17, -650,135,0)));

        put(5,new Pair<>(new CameraUtility(5, 1, 45, 45, 0),
                new Location(GameLogic.world, 731,16, -642,45,0)));

        put(6,new Pair<>(new CameraUtility(6, 1, 45, 45, 0),
                new Location(GameLogic.world, 724,17, -632,45,0)));

        put(7,new Pair<>(new CameraUtility(7, 1, 45, 45, 0),
                new Location(GameLogic.world, 731,16, -642,45,0)));

        put(8,new Pair<>(new CameraUtility(8, 1, 45, 45, 0),
                new Location(GameLogic.world, 731,16, -642,45,0)));

        put(9,new Pair<>(new CameraUtility(9, 1, 45, 45, 0),
                new Location(GameLogic.world, 731,16, -642,45,0)));






    }};
    private static double x(double i) {
        double d = i;
        d = d < 0 ? d - .5 : d + .5;
        return d;
    }
    private static double z(double i) {
        double d = i;
        d = d < 0 ? d + .5 : d - .5;
        return d;
    }

    private static HashMap<Player,Integer> AllCamsUsing = new HashMap<>();
    public static HashMap<Player, CameraUtility> AllPlayerCams = new HashMap<>();
    public static void leaveCamera(Player p,int Cam_index){
        if(AllPlayerCams.containsKey(p) && AllCamsUsing.containsKey(p)){
            initiateLeaveCameraFeed(p,Cam_index,PlayerEntities.getEntityLocation(p));
            RemoveTemplateNPC(p);
            AllPlayerCams.remove(p);
            AllCamsUsing.remove(p);
        }else{
            p.sendMessage(ChatColor.RED + Errors.getError(Camera.class,"*IN_DE"/*index doesnt exist.*/,27));

        }
    }

    public static void accessCamera(Player p,CameraUtility cu,int Camera){
        if(!AllPlayerCams.containsKey(p) && !AllCamsUsing.containsKey(p)){
            Location l =AllCams.get(Camera).getValue();
            l.setYaw(0);
            l.setPitch(0);
            cu.initCam(l);
            AllCamsUsing.put(p,cu.getIndex());
            AllPlayerCams.put(p,cu);
            givePlayerInfo(p);
            CreateTemplateNPC(p);
            initiateJoinCameraFeed(p,Camera);
        }else{
            p.sendMessage(ChatColor.RED + Errors.getError(Camera.class,"*IN_AE"/*index allready exists.*/,42));
        }
    }
    public static void initiateLeaveCameraFeed(Player p, int cam_index, Location PlayerPlaceHolderLoc){
        try {
            tryChangeToPlayerState(p);
            p.teleport(PlayerPlaceHolderLoc);
        }catch (Exception e) {
            p.sendMessage(ChatColor.RED + Errors.getError(Camera.class,"*CF_L"/*camera_feed leave*/,55));
        }
    }

    public static void initiateJoinCameraFeed(Player p,int cam_index){
        try {
            new BukkitRunnable() {
                @Override
                public void run() {
                    tryChangeToCamState(p);
                    Location l = new Location(GameLogic.world,AllCams.get(cam_index).getValue().getX(),AllCams.get(cam_index).getValue().getY(),AllCams.get(cam_index).getValue().getZ());
                    l.setY(l.getY() - 2);
                    p.teleport(l);
                }
            }.runTaskLater(GameLogic.mainThread,10);
        }catch (Exception e) {
            p.sendMessage(ChatColor.RED + Errors.getError(Camera.class,"*CF_A"/*camera_feed activate*/,59));
        }
    }
    private static boolean tryChangeToPlayerState(Player p){
        try{
            p.setInvisible(false);
            p.setGameMode(GameMode.ADVENTURE);
            p.setGravity(true);
        }catch (Exception e){
            p.sendMessage(Errors.getError(Camera.class,"*CF_L"));
        }
        return false;
    }
    private static boolean tryChangeToCamState(Player p){
        try{
            p.setInvisible(true);
            p.setGravity(false);
        }catch (Exception e){
            p.sendMessage(Errors.getError(Camera.class,"*CF_A"));
        }
        return false;
    }
    public static Pair<CameraUtility,Location> GetCamera(int i){
            return AllCams.get(i);
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


    public static SkullMeta getCustomSkull(String url,Location l) {

        if (url.isEmpty()) return null;
        Skull s = setBlockToHead(l);
        SkullMeta skullMeta = (SkullMeta)s.getData();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        profile.getProperties().put("textures", new Property("textures", url));
        try {
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        } catch (IllegalAccessException| NoSuchFieldException ex) {
            ex.printStackTrace();
        }
        l.getBlock().getState().update(true);
        return skullMeta;
    }

    public static void CreateCameras(){
        List<Pair<CameraUtility,Location>> values = new ArrayList<>(AllCams.values());
        for(int i = 0; i < values.size();i++){
            Location l = values.get(i).getValue();
            CameraUtility c = values.get(i).getKey();
            l.getBlock().setType(Material.PLAYER_HEAD);
            Skull s = (Skull)l.getBlock().getState();
            SkullMeta meta = (SkullMeta)s.getData();

            s.update(true);
        }
    }
    private static Skull setBlockToHead(Location l){
        l.getBlock().setType(Material.PLAYER_HEAD);
        Skull s = (Skull)l.getBlock().getState();
        SkullMeta meta = (SkullMeta)s.getData();
        Bukkit.getLogger().info(meta.toString());
        return s;
    }
    public static void createTestCamera(Location l){
        SkullMeta meta = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmZiNWVlZTQwYzNkZDY2ODNjZWM4ZGQxYzZjM2ZjMWIxZjAxMzcxNzg2NjNkNzYxMDljZmUxMmVkN2JmMjc4ZSJ9fX0==",l);

    }
}
