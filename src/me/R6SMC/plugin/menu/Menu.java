package me.R6SMC.plugin.menu;

import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;

    protected PlayerMenuUtility playerMenuUtility;

    public Menu(PlayerMenuUtility playerMenuUtility){
        this.playerMenuUtility = playerMenuUtility;
    }
    public abstract String getMenuName();


    public abstract int getSlots();

    public abstract void handleMenu(InventoryClickEvent e);

    public abstract void setMenuItems();


    public void Open(){
        inventory = Bukkit.createInventory(this,getSlots(),getMenuName());
        this.setMenuItems();
        DevConsole.SendDevMessage(this.playerMenuUtility.getOwner(), "TEST WINDOW OPENS SUCCESSFULLY",DevConsole.TESTING);
        new BukkitRunnable() {
            @Override
            public void run() {
                playerMenuUtility.getOwner().openInventory(inventory);
            }
        }.runTaskLater(GameLogic.mainThread,20L);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
