package me.R6SMC.plugin.Operators.Operatorhandling;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class OperatorAbilityListener implements Listener {
    @EventHandler
    public void OnAshAbil(ProjectileHitEvent event, Entity hitEntity, Block hitBlock, BlockFace hitFace){
        Entity e = event.getEntity();
    }

}
