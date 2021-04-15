package me.R6SMC.plugin.menu.MenuInstances;

import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.menu.Menu;
import me.R6SMC.plugin.menu.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import java.util.ArrayList;
import java.util.List;

public class PickDefenseOperatorMenu extends Menu {

    private List<String> DocItemLore = new ArrayList<String>(){{
        add("Docs ability is a stim gun");
        add(" that Heals himself. Be careful");
        add("because he only has Three of them!");

    }};
    private List<String> RookItemLore = new ArrayList<String>(){{
        add("Rooks ability gives absorbtion");
        add("to his whole team, adding protection");
        add("for everyone, but it can only be used");
        add("once.");
    }};
    private List<String> TachankaItemLore = new ArrayList<String>(){{
        add("NULL");
        add("NULL");
        add("NULL");
        add("NULL");
    }};

    public PickDefenseOperatorMenu(PlayerMenuUtility playerMenuUtility) {
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
            case WHITE_STAINED_GLASS_PANE:
                e.getWhoClicked().closeInventory();
                //DOC
                ((Player) e.getWhoClicked()).performCommand("defoperator doc");
                break;
            case BLUE_STAINED_GLASS_PANE:
                e.getWhoClicked().closeInventory();
                //ROOK
                DevConsole.SendDevMessage((Player)e.getWhoClicked(),"PickedRook(Not finished)",DevConsole.TESTING);
                ((Player) e.getWhoClicked()).performCommand("defoperator rook");
                break;
            case RED_STAINED_GLASS_PANE:
                e.getWhoClicked().closeInventory();
                //TACHANKA
                DevConsole.SendDevMessage((Player)e.getWhoClicked(),"Activated Tachankas Ability(Not finished)",DevConsole.TESTING);
                ((Player) e.getWhoClicked()).performCommand("defoperator tachanka");
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack Doc = new ItemStack(GameLogic.docAbil.getType(),1);
        ItemStack Rook = new ItemStack(GameLogic.rookAbil.getType(),1);
        ItemStack Tachanka = new ItemStack(GameLogic.tachankaAbil.getType(),1);
        ItemMeta DocMeta = Doc.getItemMeta();
        ItemMeta RookMeta = Rook.getItemMeta();
        ItemMeta TachankaMeta = Tachanka.getItemMeta();
        DocMeta.setDisplayName(ChatColor.BLUE + "DOC");
        RookMeta.setDisplayName(ChatColor.BLUE + "ROOK");
        TachankaMeta.setDisplayName(ChatColor.BLUE + "TACHANKA");
        DocMeta.setLore(DocItemLore);
        RookMeta.setLore(RookItemLore);
        TachankaMeta.setLore(TachankaItemLore);
        inventory.setItem(1,new ItemStack(Material.RED_STAINED_GLASS_PANE,1));
        inventory.setItem(2,new ItemStack(Material.RED_STAINED_GLASS_PANE,1));
        inventory.setItem(3,Doc);
        inventory.setItem(4,Rook);
        inventory.setItem(5,Tachanka);
        inventory.setItem(5,new ItemStack(Material.RED_STAINED_GLASS_PANE,1));
        inventory.setItem(6,new ItemStack(Material.RED_STAINED_GLASS_PANE,1));
        inventory.setItem(7,new ItemStack(Material.RED_STAINED_GLASS_PANE,1));
    }
}
