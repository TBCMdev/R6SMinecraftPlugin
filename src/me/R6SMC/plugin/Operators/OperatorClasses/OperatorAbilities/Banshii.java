package me.R6SMC.plugin.Operators.OperatorClasses.OperatorAbilities;

import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Melusi;
import me.R6SMC.plugin.Operators.Operatorhandling.CurrentOperators;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Banshii {
    private Location location;
    private Block block;
    private int rotation;
    private Player owner;
    private Material type;
    private Entity holderForGadget;
    private Cat holderType;
    public Banshii(Location l, int rotation, Player player, Material type){
        init(l,rotation,type);
        owner = player;
    }
    public Location getLocation(){
        return location;
    }
    public void init(Location l , int rotation,Material type){
        location = l;
        block = location.getBlock();
        block.setType(type);
        this.type = type;
        holderForGadget = GameLogic.world.spawnEntity(location, EntityType.CAT);
        holderType = (Cat) holderForGadget;
        holderForGadget.setSilent(true);
        holderForGadget.setInvulnerable(false);
        ((Cat) holderForGadget).setHealth(1);
        holderForGadget.setCustomName("BANSHII");
        holderForGadget.setCustomNameVisible(false);
        holderType.setInvisible(true);
    }
    public void Destroy(){
        holderType.setHealth(0);
        location.getBlock().setType(Material.AIR);
        Melusi m = (Melusi) CurrentOperators.CurrentOperators.get(owner);
        m.removeGadget(this);
    }
    private List<Player> playersWithEffect = new ArrayList<>();
    public void slowEnemy(Player enemy){
        if(!playersWithEffect.contains(enemy)) {
            PotionEffect e = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 2, false, false);
            enemy.addPotionEffect(e);
            playersWithEffect.add(enemy);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                GameLogic.world.playSound(location, Sound.BLOCK_DISPENSER_DISPENSE,2,2);
            }
        }.runTaskTimer(GameLogic.mainThread,0,5);
    }
    public void initRotation(){

    }
}
