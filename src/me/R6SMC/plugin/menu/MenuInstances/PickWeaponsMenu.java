package me.R6SMC.plugin.menu.MenuInstances;

import javafx.util.Pair;
import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Ash;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Capitao;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Finka;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Aruni;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Doc;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Rook;
import me.R6SMC.plugin.Operators.Operatorhandling.CurrentOperators;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.menu.Menu;
import me.R6SMC.plugin.menu.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PickWeaponsMenu extends Menu {
    static String p = ChatColor.LIGHT_PURPLE.toString();
    public static HashMap<Class, Pair<String/*name*/, String/*lore*/>> OperatorLoadouts = new HashMap<Class, Pair<String, String>>() {{
        put(Doc.class, new Pair<>(ChatColor.DARK_BLUE + "Loadout 1", ChatColor.BLUE + "Contents: \n " + p
                + "MP5(SMG)\nglock17(SEMI)"));
        put(Rook.class, new Pair<>(ChatColor.DARK_BLUE + "Loadout 1", ChatColor.BLUE + "Contents: \n " + p
                + "MP5(SMG)\ndesert eagle(SEMI)"));
        put(Aruni.class, new Pair<>(ChatColor.DARK_BLUE + "Loadout 1", ChatColor.BLUE + "Contents: \n " + p
                + "M500(Shotgun)\nmac10(FA)"));
        put(Ash.class, new Pair<>(ChatColor.DARK_BLUE + "Loadout 1", ChatColor.BLUE + "Contents: \n " + p
                + "M4(AR)\nglock18(FA)"));
        put(Capitao.class, new Pair<>(ChatColor.DARK_BLUE + "Loadout 1", ChatColor.BLUE + "Contents: \n " + p
                + "g3(DMR/AR)\nglock18(FA)"));
        put(Finka.class, new Pair<>(ChatColor.DARK_BLUE + "Loadout 1", ChatColor.BLUE + "Contents: \n " + p
                + "m16(DMR)\nmakarov(SEMI)"));
    }};

    public PickWeaponsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "PICK A LOADOUT";
    }

    @Override
    public int getSlots() {
        return GameChat.GetAllPlayers().size();
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        DevConsole.SendDevMessage((Player) e.getWhoClicked(), "Handling menu", DevConsole.TESTING);
        switch (e.getCurrentItem().getType()) {
            case GREEN_CONCRETE:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("loadout 1")){
                    CurrentOperators.getPlayerWithOp(playerMenuUtility.getOwner()).picked_Loadout = 1;
                }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("loadout 2")){
                    CurrentOperators.getPlayerWithOp(playerMenuUtility.getOwner()).picked_Loadout = 2;

                }
                GameLogic.ResendTeleportRed();
                GameLogic.ResendTeleportBlue();
                break;
        }
    }

    public List<Pair<String,String>> getAllOperatorLoadouts(Player p) {
        List<Pair<String,String>> list = new ArrayList<>();
        for(Class c : OperatorLoadouts.keySet()){
            switch (CurrentOperators.getPlayerWithOp(p).getName().toLowerCase()) {
                case "doc":
                    if(c.getSimpleName().equalsIgnoreCase("doc")){
                        list.add(OperatorLoadouts.get(c));
                    }
                    break;
                case "rook":
                    if(c.getSimpleName().equalsIgnoreCase("rook")){
                        list.add(OperatorLoadouts.get(c));
                    }
                    break;
                case "aruni":
                    if(c.getSimpleName().equalsIgnoreCase("aruni")){
                        list.add(OperatorLoadouts.get(c));
                    }
                    break;
                case "ash":
                    if(c.getSimpleName().equalsIgnoreCase("ash")){
                        list.add(OperatorLoadouts.get(c));
                    }
                    break;
                case "capitao":
                    if(c.getSimpleName().equalsIgnoreCase("capitao")){
                        list.add(OperatorLoadouts.get(c));
                    }
                    break;
                case "finka":
                    if(c.getSimpleName().equalsIgnoreCase("finka")){
                        list.add(OperatorLoadouts.get(c));
                    }
                    break;
                case "bandit":
                    if(c.getSimpleName().equalsIgnoreCase("bandit")){
                        list.add(OperatorLoadouts.get(c));
                    }
                    break;
                case "dokkaebi":
                    if(c.getSimpleName().equalsIgnoreCase("dokkaebi")){
                        list.add(OperatorLoadouts.get(c));
                    }                    break;

            }
        }
        return list;
    }

    @Override
    public void setMenuItems() {
        List<Pair<String,String>> loadouts = getAllOperatorLoadouts(playerMenuUtility.getOwner());
        List<ItemStack> stacks = new ArrayList<>();
        List<String> contents = new ArrayList<>();
        List<String> names = new ArrayList<>();
        ItemStack current = new ItemStack(Material.GREEN_CONCRETE,1);
        for(Pair<String,String> st : loadouts){
            String str = st.getValue();
            String stri = st.getKey();
            contents.add(str);
            names.add(stri);
        }
        for(Pair<String,String> s : loadouts){
            current = new ItemStack(Material.GREEN_CONCRETE,1);
            ItemMeta meta = current.getItemMeta();
            meta.setLore(contents);
            stacks.add(current);
        }
        for(String s : names){
            current.getItemMeta().setDisplayName(s);

        }
        ItemStack[] i = new ItemStack[stacks.size()];
        stacks.toArray(i);
        inventory.setContents(i);
    }
}
