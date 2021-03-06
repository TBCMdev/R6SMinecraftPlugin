package me.R6SMC.plugin.Listeners;

import javafx.util.Pair;
import me.R6SMC.plugin.Cameras.Camera;
import me.R6SMC.plugin.Cameras.CameraUtility;
import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.Errors;
import me.R6SMC.plugin.GameLogic.GameLogic;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class CameraListener implements Listener {
    private static List<Player> PlayersWithCooldown = new ArrayList<>();

    @EventHandler
    public void PlayerMoveOnCam(PlayerMoveEvent e) {

        if (Camera.GetAllCamsUsing().containsKey(e.getPlayer())) {
            Location locWithoutRot = Camera.getAllPlayerCamsUsing().get(e.getPlayer()).getLocation();
            Location loc = new Location(GameLogic.world,locWithoutRot.getX(),locWithoutRot.getY(),locWithoutRot.getZ(),e.getTo().getYaw(), e.getTo().getPitch());
            e.getPlayer().teleport(loc);
        try {
            CameraUtility util = Camera.AllPlayerCams.get(e.getPlayer());
            Pair<Float, Float> MaxRots = util.getRots();
            Pair<Float, Float> DefRots = util.getDefRots();
            if (e.getPlayer().getLocation().getPitch() > MaxRots.getValue()) {
                //YAW + X PITCH = Y
                Location l = new Location(GameLogic.world, e.getFrom().getX(), e.getFrom().getY(), e.getFrom().getZ(),e.getPlayer().getLocation().getYaw(), MaxRots.getValue());
                e.getPlayer().teleport(l);
            }
            if (e.getPlayer().getLocation().getPitch() < -MaxRots.getValue()) {
                //YAW + X PITCH = Y
                Location l = new Location(GameLogic.world, e.getFrom().getX(), e.getFrom().getY(), e.getFrom().getZ(),e.getPlayer().getLocation().getYaw(), -MaxRots.getValue());
                e.getPlayer().teleport(l);
            }
            /*else {
                Location l = new Location(GameLogic.world, e.getFrom().getX(), e.getFrom().getY(), e.getFrom().getZ(), DefRots.getKey(), DefRots.getValue());
                e.getPlayer().teleport(l);
            }*/
        }catch (Exception ex){
            e.getPlayer().sendMessage(ChatColor.RED + "could not get camera util");
        }
    }

}


    public void addPlayerToCooldown(Player p){
        try {
            if (!PlayersWithCooldown.contains(p)) {
                PlayersWithCooldown.add(p);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        PlayersWithCooldown.remove(p);
                        DevConsole.SendDevMessage(p, "you can now enter cams again.", DevConsole.TESTING);
                    }
                }.runTaskLater(GameLogic.mainThread, 20L);
            } else {
                PlayersWithCooldown.remove(p);
            }
        }catch (Exception e){
            p.sendMessage(ChatColor.RED + Errors.getError(CameraListener.class,"*PN_AC"/*player not added to cooldown*/,56));
        }
    }
    /*public void tryToAccessCamera(PlayerInteractEvent e){
        if(GameLogic.GameStarted) {
            if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            if (item.getType() == Material.PLAYER_HEAD) {
                ItemMeta meta = item.getItemMeta();
                if (meta.getDisplayName().equalsIgnoreCase("ACCESS CAMERAS")) {
                    Pair<CameraUtility, Location> pair = Camera.GetCamera();
                    Camera.accessCamera(e.getPlayer(), pair.getKey(), pair.getKey().getIndex());
                }
            }
        }
    }*/
        @EventHandler
        public void tryToLeaveCamera(PlayerDropItemEvent e){
            ItemStack item = e.getItemDrop().getItemStack();
            if(GameLogic.GameStarted) {
                if (item.getType() == Material.PLAYER_HEAD) {
                    ItemMeta meta = item.getItemMeta();
                    if (meta.getDisplayName().equalsIgnoreCase("ACCESS CAMERAS")) {
                        try {
                            if (Camera.getPlayerUsingCam(e.getPlayer()) > -1) {
                                Camera.leaveCamera(e.getPlayer(), Camera.getPlayerUsingCam(e.getPlayer()));
                            } else {
                                e.getPlayer().sendMessage(ChatColor.RED + Errors.getError(Camera.class, "*CL_C"/*cannot leave camera*/, 82));
                            }
                        } catch (Exception ex) {
                            e.getPlayer().sendMessage(ChatColor.RED + Errors.getError(Camera.class, "*CL_C"/*cannot leave camera*/, 82));
                        }
                    }
                }
            }
        }

}
