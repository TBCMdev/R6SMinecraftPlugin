package me.R6SMC.plugin.Listeners;

import me.R6SMC.plugin.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;

public class MenuListener implements Listener {

    @EventHandler
    public void OnMenuClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        InventoryHolder holder = e.getClickedInventory().getHolder();
        if(holder instanceof Menu){
            e.setCancelled(true);
            if(e.getCurrentItem() == null){
                return;
            }
            Menu menu = (Menu) holder;
            menu.handleMenu(e);
        }
    }
}
