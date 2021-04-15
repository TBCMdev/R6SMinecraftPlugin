package me.R6SMC.plugin.GameLogic;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import jdk.nashorn.internal.parser.JSONParser;
import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.Main;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public String[] getFromPlayer(Player playerBukkit) {
        EntityPlayer playerNMS = ((CraftPlayer) playerBukkit).getHandle();
        GameProfile profile = playerNMS.getProfile();
        Property property = profile.getProperties().get("textures").iterator().next();
        String texture = property.getValue();
        String signature = property.getSignature();
        return new String[] {texture, signature};
    }
    public String[] getFromName(String name) {
        try {
            URL url_0 = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            InputStreamReader reader_0 = new InputStreamReader(url_0.openStream());
            String uuid = new JsonParser().parse(reader_0).getAsJsonObject().get("id").getAsString();

            URL url_1 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            InputStreamReader reader_1 = new InputStreamReader(url_1.openStream());
            JsonObject textureProperty = new JsonParser().parse(reader_1).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            String texture = textureProperty.get("value").getAsString();
            String signature = textureProperty.get("signature").getAsString();

            return new String[] {texture, signature};
        } catch (IOException e) {
            System.err.println("Could not get skin data from session servers!");
            e.printStackTrace();
            return null;
        }
    }
    public void addNpc(int x, int y, int z) {
        String[] a = getFromName("notch");
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "notchy");
        profile.getProperties().put("textures", new Property("textures", a[0], a[1]));
        EntityPlayer npc = new EntityPlayer(server, GameLogic.world, profile, new PlayerInteractManager(Bukkit.getServer()));
        npc.setPosition(x, y, z);
        for (Player player : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            Bukkit.getScheduler().scheduleSyncDelayedTask(GameLogic.mainThread, new Runnable() {
                @Override
                public void run() {
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
                }
            }, 5);
        }
    }

}
