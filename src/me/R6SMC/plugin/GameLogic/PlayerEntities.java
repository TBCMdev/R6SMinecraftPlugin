package me.R6SMC.plugin.GameLogic;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;

import java.util.ArrayList;
import java.util.List;

public class PlayerEntities {
    public static EntityType CurrentSpawnType = EntityType.ARMOR_STAND;
    public static void CreateEntities(List<Player> players, List<Location> locations){
        MinecraftServer server = ((CraftServer)Bukkit.getServer()).getServer();
        List<Skeleton> AllPlaceHolders = new ArrayList<>();
        for(Location l : locations) {
            Skeleton PlayerPlaceHolder = (Skeleton) GameLogic.world.spawnEntity(l,EntityType.SKELETON);
            AllPlaceHolders.add(PlayerPlaceHolder);
        }
        for(int i = 0; i < AllPlaceHolders.size();i++){
                Skeleton currentSkeleton = AllPlaceHolders.get(i);
                Player CurrentPlayer = players.get(i);
                currentSkeleton.setCustomName(CurrentPlayer.getDisplayName());
                currentSkeleton.setAI(false);
        }
        //EntityPlayer player = new EntityPlayer(server,GameLogic.world,);
    }
}
