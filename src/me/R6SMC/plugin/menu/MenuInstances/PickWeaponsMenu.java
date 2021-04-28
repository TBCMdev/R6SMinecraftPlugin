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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PickWeaponsMenu extends Menu {
    static String p = ChatColor.LIGHT_PURPLE.toString();
    public static HashMap<Class, Pair<String/*name*/, String[]>> OperatorLoadouts = new HashMap<Class, Pair<String, String[]>>() {{
        put(Doc.class, new Pair<>(ChatColor.DARK_BLUE + "Loadout 1",new String[]{ ChatColor.BLUE + "Contents: ",p
                + "MP5(SMG)",p + "glock17(SEMI)"}));
        put(Rook.class, new Pair<>(ChatColor.DARK_BLUE + "Loadout 1",new String[]{ ChatColor.BLUE + "Contents: ", p
                + "MP5(SMG)",p +"desert eagle(SEMI)"}));
        put(Aruni.class, new Pair<>(ChatColor.DARK_BLUE + "Loadout 1",new String[]{ ChatColor.BLUE + "Contents: ", p
                + "M500(Shotgun)",p +"mac10(FA)"}));
        put(Ash.class, new Pair<>(ChatColor.DARK_BLUE + "Loadout 1",new String[]{ ChatColor.BLUE + "Contents: ", p
                + "M4(AR)",p +"glock18(FA)"}));
        put(Capitao.class, new Pair<>(ChatColor.DARK_BLUE + "Loadout 1",new String[]{ ChatColor.BLUE + "Contents: ", p
                + "g3(DMR/AR)",p +"glock18(FA)"}));
        put(Finka.class, new Pair<>(ChatColor.DARK_BLUE + "Loadout 1",new String[]{ ChatColor.BLUE + "Contents: ", p
                + "m16(DMR)",p +"makarov(SEMI)"}));
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
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        DevConsole.SendDevMessage((Player) e.getWhoClicked(), "Handling menu", DevConsole.TESTING);
        switch (e.getCurrentItem().getType()) {
            case GREEN_CONCRETE:
                if(e.getCurrentItem().getItemMeta().getDisplayName().contains("1")){
                    CurrentOperators.getPlayerWithOp(playerMenuUtility.getOwner()).picked_Loadout = 1;
                }else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("2")){
                    CurrentOperators.getPlayerWithOp(playerMenuUtility.getOwner()).picked_Loadout = 2;

                }else{
                    DevConsole.SendDevMessage(((Player) e.getWhoClicked()).getPlayer(),"returning out of method...",DevConsole.TESTING);
                    return;
                }
                //the teleporting should happen here
                GameLogic.ResendTeleportRed();
                GameLogic.ResendTeleportBlue();
                break;
        }
    }

    public List<Pair<String,String[]>> getAllOperatorLoadouts(Player p) {
        List<Pair<String,String[]>> list = new ArrayList<>();
        for(Class c : OperatorLoadouts.keySet()){
            switch (CurrentOperators.getPlayerWithOp(p).getName().toLowerCase()) {
                case "doc":
                    if(c.getSimpleName().equalsIgnoreCase("doc")){
                        p.sendMessage(ChatColor.LIGHT_PURPLE + c.getSimpleName());
                        p.sendMessage(ChatColor.DARK_PURPLE + CurrentOperators.getPlayerWithOp(p).getName().toLowerCase());

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
                    }
                    break;
            }
        }
        return list;
    }

    @Override
    public void setMenuItems() {
        List<Pair<String,String[]>> loadouts = getAllOperatorLoadouts(playerMenuUtility.getOwner());
        List<ItemStack> stacks = new ArrayList<>();
        ItemStack current = new ItemStack(Material.GREEN_CONCRETE,1);
        ItemMeta meta = current.getItemMeta();

        for(Pair<String,String[]> st : loadouts){
            String[] str = st.getValue();
            String s = st.getKey();
            meta.setLore(Arrays.asList(str.clone()));
            meta.setDisplayName(s);
            current.setItemMeta(meta);
            stacks.add(current);
            current = new ItemStack(Material.GREEN_CONCRETE,1);
        }
        ItemStack[] i = new ItemStack[stacks.size()];
        stacks.toArray(i);
        inventory.setContents(i);
    }
}
