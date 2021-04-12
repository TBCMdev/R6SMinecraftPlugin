package me.R6SMC.plugin.Operators;

import me.R6SMC.plugin.GameLogic;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class OperatorAbilityListener implements Listener {

    /*public void OnAshAbil(ProjectileHitEvent event, Entity hitEntity, Block hitBlock, BlockFace hitFace){
        Player p = (Player) event.get;
        Bukkit.getLogger().info("you hit soomething");
        p.sendMessage("nice shot!");
        Entity entity = event.getEntity();

        Block block = event.getHitBlock();

        if(shooter == GameLogic.ash) {
            if(block.getRelative(BlockFace.NORTH).getType().equals(Material.DARK_OAK_SLAB)) {
                Block relativeBlock = block.getRelative(BlockFace.NORTH);
                relativeBlock.setType(Material.AIR);
            }
            if(block.getRelative(BlockFace.SOUTH).getType().equals(Material.DARK_OAK_SLAB)) {
                Block relativeBlock = block.getRelative(BlockFace.SOUTH);
                relativeBlock.setType(Material.AIR);
            }
            if(block.getRelative(BlockFace.EAST).getType().equals(Material.DARK_OAK_SLAB)) {
                Block relativeBlock = block.getRelative(BlockFace.EAST);
                relativeBlock.setType(Material.AIR);
            }
            if(block.getRelative(BlockFace.WEST).getType().equals(Material.DARK_OAK_SLAB)){
                Block relativeBlock = block.getRelative(BlockFace.NORTH);
                relativeBlock.setType(Material.AIR);
            }

        }
    }*/

}
