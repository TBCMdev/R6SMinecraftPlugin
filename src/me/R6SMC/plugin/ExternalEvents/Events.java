package me.R6SMC.plugin.ExternalEvents;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Events implements Listener {
   @EventHandler
    public void KillEntity(EntityDeathEvent e){
       if(e.getEntityType() == EntityType.CREEPER) {
           List<ItemStack> Drops = e.getDrops();
           for (ItemStack i : Drops) {
               i.setType(Material.DIAMOND);
           }
       }
   }
}
