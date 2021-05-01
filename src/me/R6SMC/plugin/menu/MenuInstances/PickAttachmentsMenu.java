package me.R6SMC.plugin.menu.MenuInstances;

import javafx.util.Pair;
import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Loadouts.Loadouts;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Ash;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Capitao;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Finka;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Aruni;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Doc;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Rook;
import me.R6SMC.plugin.Operators.Operatorhandling.CurrentOperators;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.PlayerLogic.Organiser;
import me.R6SMC.plugin.menu.Menu;
import me.R6SMC.plugin.menu.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PickAttachmentsMenu extends Menu {
    static String p = ChatColor.LIGHT_PURPLE.toString();
    public static HashMap<String[], Pair<String/*name*/, String[]>> OperatorAttachments = new HashMap<String[], Pair<String, String[]>>() {{
        put(getGunsWithCertainAttachment("acog"),
                new Pair<>(ChatColor.DARK_BLUE + "Acog",new String[]{ ChatColor.BLUE + p
                + "PRIMARY"}));
        put(getGunsWithCertainAttachment("holo"),
                new Pair<>(ChatColor.DARK_BLUE + "Holo",new String[]{ ChatColor.BLUE + p
                        + "PRIMARY"}));
        put(getGunsWithCertainAttachment("holo"),
                new Pair<>(ChatColor.DARK_BLUE + "Holo",new String[]{ ChatColor.BLUE + p
                        + "SECONDARY"}));
    }};
    private static String[] getGunsWithCertainAttachment(String attachment){
        ArrayList<String[]> GunNames = new ArrayList<>(Organiser.AvailableScopes.keySet());
        ArrayList<String[]> GunsThatSupportAttachments = new ArrayList<>();
        for(String[] s : GunNames){
            for(String st : Organiser.AvailableScopes.get(s).getKey()){
                if(st.equalsIgnoreCase(attachment)){
                    GunsThatSupportAttachments.add(s);
                }
            }
        }
        return (String[])GunsThatSupportAttachments.toArray();
    }
    public PickAttachmentsMenu(PlayerMenuUtility playerMenuUtility) {
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
    public boolean isPrimary(ItemMeta meta){
        if(meta.getLore().contains("SECONDARY")){
            return false;
        }
        return true;
    }
    @Override
    public void handleMenu(InventoryClickEvent e) {
        DevConsole.SendDevMessage((Player) e.getWhoClicked(), "Handling menu", DevConsole.TESTING);
        switch (e.getCurrentItem().getType()) {
            case GREEN_CONCRETE:
                if(e.getCurrentItem().getItemMeta().getDisplayName().contains("acog")){
                    CurrentOperators.getPlayerWithOp(playerMenuUtility.getOwner()).primary_attachments = Operator.picked_primary_attachments.acog;
                }else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("holo")){
                    CurrentOperators.getPlayerWithOp(playerMenuUtility.getOwner()).primary_attachments = Operator.picked_primary_attachments.holographic;

                }else{
                    DevConsole.SendDevMessage(((Player) e.getWhoClicked()).getPlayer(),"returning out of method...",DevConsole.TESTING);
                    return;
                }
                //the teleporting should happen here
                GameLogic.ResendTeleportTeams();
                break;
        }
    }

    public List<Pair<String,String[]>> getAllOperatorLoadouts(Player p) {
        List<Pair<String,String[]>> list = new ArrayList<>();
        for(String[] c : OperatorAttachments.keySet()){
            switch (CurrentOperators.getPlayerWithOp(p).getName().toLowerCase()) {
                case "doc":
                    if(c.equalsIgnoreCase("doc")){
                        list.add(OperatorAttachments.get(c));
                    }
                    break;
                case "rook":
                    if(c.getSimpleName().equalsIgnoreCase("rook")){
                        list.add(OperatorAttachments.get(c));
                    }
                    break;
                case "aruni":
                    if(c.getSimpleName().equalsIgnoreCase("aruni")){
                        list.add(OperatorAttachments.get(c));
                    }
                    break;
                case "ash":
                    if(c.getSimpleName().equalsIgnoreCase("ash")){
                        list.add(OperatorAttachments.get(c));
                    }
                    break;
                case "capitao":
                    if(c.getSimpleName().equalsIgnoreCase("capitao")){
                        list.add(OperatorAttachments.get(c));
                    }
                    break;
                case "finka":
                    if(c.getSimpleName().equalsIgnoreCase("finka")){
                        list.add(OperatorAttachments.get(c));
                    }
                    break;
                case "bandit":
                    if(c.getSimpleName().equalsIgnoreCase("bandit")){
                        list.add(OperatorAttachments.get(c));
                    }
                    break;
                case "dokkaebi":
                    if(c.getSimpleName().equalsIgnoreCase("dokkaebi")){
                        list.add(OperatorAttachments.get(c));
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
