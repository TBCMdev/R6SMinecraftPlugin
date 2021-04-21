package me.R6SMC.plugin.Operators.OperatorClasses.Defense;

import javafx.util.Pair;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import me.R6SMC.plugin.PlayerLogic.PlayerClass;
import me.R6SMC.plugin.Sounds.Sounds;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Aruni extends Operator {
    private final float RangeAid = 3;
    private HashMap<Integer, Location[]> DoorLocations = new HashMap<Integer, Location[]>() {{
        put(0, new Location[]{
                new Location(GameLogic.world, 719, 14, -651),
                new Location(GameLogic.world, 719, 15, -651),
                new Location(GameLogic.world, 720, 14, -650),
                new Location(GameLogic.world, 720, 15, -650)
        });
        put(1, new Location[]{
                new Location(GameLogic.world, 725, 14, -649),
                new Location(GameLogic.world, 725, 15, -649)
        });
        put(2, new Location[]{
                new Location(GameLogic.world, 716, 14, -660),
                new Location(GameLogic.world, 716, 15, -660)
        });
        put(3, new Location[]{
                new Location(GameLogic.world, 717, 14, -654),
                new Location(GameLogic.world, 717, 15, -654)
        });
        put(4, new Location[]{
                new Location(GameLogic.world, 721, 14, -640),
                new Location(GameLogic.world, 721, 15, -640)
        });
        put(5, new Location[]{
                new Location(GameLogic.world, 717, 14, -635),
                new Location(GameLogic.world, 717, 15, -635)
        });
        put(6, new Location[]{
                new Location(GameLogic.world, 707, 14, -637),
                new Location(GameLogic.world, 707, 15, -637)
        });
        //SECOND FLOOR
        put(7, new Location[]{
                new Location(GameLogic.world, 717, 9, -636),
                new Location(GameLogic.world, 717, 10, -636)
        });
        put(8, new Location[]{
                new Location(GameLogic.world, 709, 9, -640),
                new Location(GameLogic.world, 709, 10, -640),
                new Location(GameLogic.world, 710, 9, -640),
                new Location(GameLogic.world, 710, 10, -640)
        });
        put(9, new Location[]{
                new Location(GameLogic.world, 707, 9, -645),
                new Location(GameLogic.world, 707, 10, -645),
                new Location(GameLogic.world, 707, 9, -646),
                new Location(GameLogic.world, 707, 10, -646)
        });
        put(10, new Location[]{
                new Location(GameLogic.world, 716, 9, -646),
                new Location(GameLogic.world, 716, 10, -646)
        });
        put(11, new Location[]{
                new Location(GameLogic.world, 726, 9, -642),
                new Location(GameLogic.world, 726, 10, -642)
        });
        put(12, new Location[]{
                new Location(GameLogic.world, 728, 10, -645)
        });
        put(13, new Location[]{
                new Location(GameLogic.world, 725, 4, -635),
                new Location(GameLogic.world, 725, 5, -635)
        });
        put(14, new Location[]{
                new Location(GameLogic.world, 726, 4, -648),
                new Location(GameLogic.world, 726, 5, -648)
        });
        put(15, new Location[]{
                new Location(GameLogic.world, 717, 4, -635),
                new Location(GameLogic.world, 717, 5, -635),
                new Location(GameLogic.world, 717, 4, -634),
                new Location(GameLogic.world, 717, 5, -634)
        });
        put(16, new Location[]{
                new Location(GameLogic.world, 704, 4, -636),
                new Location(GameLogic.world, 704, 5, -636)
        });
        put(17, new Location[]{
                new Location(GameLogic.world, 702, 4, -630),
                new Location(GameLogic.world, 702, 5, -630)
        });
    }};//ADD door locations
    private boolean CanUseAbility = true;
    private boolean ShowParticles = true;
    private boolean IsRunningParticles = false;
    public OperatorUtility PlayerManager;
    private static List<Location[]> DoorsWithAbilities = new ArrayList<>();

    public Aruni(OperatorUtility ou) {
        super(3, ou.getOwner(), ou.getOwnerClass(), "aruni");
        PlayerManager = ou;
    }

    public void CancelHoldAbility() {
        ShowParticles = false;

    }

    @Override
    public void HoldAbility() {
        if (GameLogic.GameStarted && !IsRunningParticles) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    IsRunningParticles = true;
                    if (ShowParticles) {
                        Location origin = player.getEyeLocation().subtract(new Location(GameLogic.world, 0, 0, 0));
                        Vector direction = origin.getDirection();
                        direction.multiply(10 /* the range */);
                        direction.normalize();
                        for (int i = 0; i < 10 /* range */; i++) {
                            Location loc = origin.add(direction);
                            PlayerManager.getOwner().spawnParticle(Particle.ASH, loc, 2);
                        }
                    } else {
                        ShowParticles = true;
                        IsRunningParticles = false;
                        cancel();
                        return;
                    }
                }
            }.runTaskTimer(GameLogic.mainThread, 0, 1);
        }

    }

    private boolean CoolingAbility = false;

    @Override
    public void AbilityCooldown() {
        CanUseAbility = false;
        if (!CoolingAbility) {
            CoolingAbility = true;
            new BukkitRunnable() {

                @Override
                public void run() {
                    CanUseAbility = true;
                    ShowParticles = true;
                    CoolingAbility = false;
                }
            }.runTaskLater(GameLogic.mainThread, 20L);
        }
    }

    @Override
    public void ResetAbility() {

    }

    @Override
    public void SetAbility() {

    }

    public static void ReloadClass() {
        for (Location[] lo : DoorsWithAbilities) {
            for (Location l : lo) {
                Block b = l.getBlock();
                b.setType(Material.AIR);
            }
        }
        DoorsWithAbilities.clear();
    }

    public int PlayerIsLookingAtDoor() {
        Block current = player.getTargetBlock((Set<Material>) null, 5).getLocation().add(0, 1, 0).getBlock();
        Location currentPos = current.getLocation();
        List<Location[]> locations = new ArrayList<>(DoorLocations.values());
        Location[] nearestDoor = locations.get(0);
        double minvalue = Integer.MAX_VALUE;
        for (Location[] l : locations) {
            if (!DoorsWithAbilities.contains(l)) {
                Bukkit.getLogger().info(l.toString());
                double value = Math.sqrt(Math.pow((l[0].getX() - currentPos.getX()), 2) + Math.pow((l[0].getY() - currentPos.getY()), 2) + Math.pow((l[0].getZ() - currentPos.getZ()), 2));
                if (value < minvalue) {
                    minvalue = value;
                    nearestDoor = l;
                    player.sendMessage("value: " + ChatColor.GOLD + minvalue + "door index:" + locations.indexOf(nearestDoor));
                }
            }

        }
        if (minvalue < RangeAid) {
            return locations.indexOf(nearestDoor);
        }else{
            player.sendMessage(ChatColor.RED + "Door already electrified.");
        }

        return -1;
    }

    @Override
    public void activateAbility() {
        if (GameLogic.GameStarted) {
            if (CanUseAbility) {
                ShowParticles = false;
                int index = PlayerIsLookingAtDoor();
                if (index >= 0) {
                    Location[] l = DoorLocations.get(index);
                    Sounds.PlaySound(Sound.BLOCK_GLASS_PLACE, player);
                    for (Location lo : l) {
                        Block b = lo.getBlock();
                        b.setType(Material.COBWEB);
                        if (!DoorsWithAbilities.contains(l)) {
                            DoorsWithAbilities.add(l);
                        }
                    }
                    AbilityCooldown();
                }
            } else {
                PlayerManager.getOwner().sendMessage(ChatColor.RED + "ability cooldown!");
            }
        }

    }
}
