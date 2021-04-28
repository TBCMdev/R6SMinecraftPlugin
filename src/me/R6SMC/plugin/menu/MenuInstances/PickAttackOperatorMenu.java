package me.R6SMC.plugin.menu.MenuInstances;

import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.menu.Menu;
import me.R6SMC.plugin.menu.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PickAttackOperatorMenu extends Menu {

    private List<String> AshItemLore = new ArrayList<String>(){{
        add("Ashs ability is an explosive");
        add(" that drills into walls and explodes");
        add("it! she has 3 shots.");

    }};
    private List<String> CapitaoItemLore = new ArrayList<String>(){{
        add("Capitaos ability is a Crossbow");
        add("that shoot a ball of fire that");
        add("your enemies do not want to step in!");
        add("he has 2 shots.");
    }};
    private List<String> FinkaItemLore = new ArrayList<String>(){{
        add("Finkas ability is a boost that");
        add("she gives to all of her teammates.");
        add("everyone on her team gets to tank more");
        add("bullets! she has 3 boosts.");
    }};

    public PickAttackOperatorMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "PICK AN OPERATOR";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        DevConsole.SendDevMessage((Player)e.getWhoClicked(),"Handling menu" , DevConsole.TESTING);
        switch (e.getCurrentItem().getType()){
            case GREEN_STAINED_GLASS_PANE:
                e.getWhoClicked().closeInventory();
                DevConsole.SendDevMessage((Player)e.getWhoClicked(),"Picked Ash by Menu(Not Finished)",DevConsole.TESTING);
                ((Player) e.getWhoClicked()).performCommand("attkoperator ash");
                break;
            case BROWN_STAINED_GLASS_PANE:
                e.getWhoClicked().closeInventory();
                DevConsole.SendDevMessage((Player)e.getWhoClicked(),"Picked finka by Menu(Not Finished)",DevConsole.TESTING);
                ((Player) e.getWhoClicked()).performCommand("attkoperator finka");
                break;
            case GRAY_STAINED_GLASS_PANE:
                e.getWhoClicked().closeInventory();
                DevConsole.SendDevMessage((Player)e.getWhoClicked(),"Picked capitao by Menu(Not Finished)",DevConsole.TESTING);
                ((Player) e.getWhoClicked()).performCommand("attkoperator capitao");
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack Ash = new ItemStack(GameLogic.ashAbil.getType(),1);
        ItemStack Capitao = new ItemStack(GameLogic.capitaoAbil.getType(),1);
        ItemStack Finka = new ItemStack(GameLogic.finkaAbil.getType(),1);
        ItemMeta AshMeta = Ash.getItemMeta();
        ItemMeta CapitaoMeta = Capitao.getItemMeta();
        ItemMeta FinkaMeta = Finka.getItemMeta();
        AshMeta.setDisplayName(ChatColor.RED + "ASH");
        CapitaoMeta.setDisplayName(ChatColor.RED + "CAPITAO");
        FinkaMeta.setDisplayName(ChatColor.RED + "FINKA");
        AshMeta.setLore(AshItemLore);
        CapitaoMeta.setLore(CapitaoItemLore);
        FinkaMeta.setLore(FinkaItemLore);
        Ash.setItemMeta(AshMeta);
        Finka.setItemMeta(FinkaMeta);
        Capitao.setItemMeta(CapitaoMeta);
        inventory.setItem(0,new ItemStack(Material.RED_STAINED_GLASS_PANE,1));
        inventory.setItem(1,new ItemStack(Material.RED_STAINED_GLASS_PANE,1));
        inventory.setItem(2,new ItemStack(Material.RED_STAINED_GLASS_PANE,1));
        inventory.setItem(3,Ash);
        inventory.setItem(4,Capitao);
        inventory.setItem(5,Finka);
        inventory.setItem(6,new ItemStack(Material.RED_STAINED_GLASS_PANE,1));
        inventory.setItem(7,new ItemStack(Material.RED_STAINED_GLASS_PANE,1));
        inventory.setItem(8,new ItemStack(Material.RED_STAINED_GLASS_PANE,1));
    }
}
