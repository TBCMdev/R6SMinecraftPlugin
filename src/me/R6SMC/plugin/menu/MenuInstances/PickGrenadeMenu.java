package me.R6SMC.plugin.menu.MenuInstances;

import javafx.util.Pair;
import me.R6SMC.plugin.Operators.OperatorClasses.OperatorAbilities.Grenade;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.menu.Menu;
import me.R6SMC.plugin.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PickGrenadeMenu extends Menu {
    public static HashMap<Grenade.GrenadeType, String[]> Grenades = new HashMap<Grenade.GrenadeType,String[]>(){{
        ArrayList<Grenade.GrenadeType> allTypes = new ArrayList<>();
        allTypes.addAll(Arrays.asList(Grenade.GrenadeType.values()));
        ArrayList<String[]> allDescriptions = new ArrayList<>();
        for(Grenade g : Grenade.allGrenadeDefaultValues){
            List<String> l = new ArrayList<String>();
            for(Object o : g.getDefaults()){
                l.add(o.toString() + "\n");
            }
            allDescriptions.add((String[])l.toArray());
        }
    }};
    public PickGrenadeMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "PICK A GRENADE";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

    }

    @Override
    public void setMenuItems() {
        List<ItemStack> allGrenades = new ArrayList<>();
        for(Operator.primary_grenade g : Operator.primary_grenade.values()){
            ItemStack item = new ItemStack(Material.GREEN_CONCRETE,1);
            allGrenades.add(item);
        }
        for()
    }
}
