package me.R6SMC.plugin.menu;

import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.scheduler.BukkitRunnable;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public static List<Class<? extends Menu>> getInheritedClasses(){
        Reflections ref = new Reflections("me.R6SMC.plugin");
        Set<Class<? extends Menu>> inheritedClasses = ref.getSubTypesOf(Menu.class);
        return new ArrayList<>(inheritedClasses);
    }

    public void Open(){
        inventory = Bukkit.createInventory(this,getSlots(),getMenuName());
        this.setMenuItems();
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
