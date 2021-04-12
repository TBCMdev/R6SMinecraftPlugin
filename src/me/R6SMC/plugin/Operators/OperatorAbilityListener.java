package me.R6SMC.plugin.Operators;

import me.R6SMC.plugin.GameLogic;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Snow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class OperatorAbilityListener implements Listener {
    @EventHandler
    public void OnAshAbil(ProjectileHitEvent event, Entity hitEntity, Block hitBlock, BlockFace hitFace){
        Entity e = event.getEntity();
        if(e instanceof Snowball){

        }
    }

}
