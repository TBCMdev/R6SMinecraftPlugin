package me.R6SMC.plugin.menu.MenuInstances;

import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
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
import java.util.List;

public class KickPlayerMenu extends Menu {

    public KickPlayerMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "KILL A TOXIC PLAYER";
    }

    @Override
    public int getSlots() {
        return GameChat.GetAllPlayers().size();
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        DevConsole.SendDevMessage((Player)e.getWhoClicked(),"Handling menu" , DevConsole.TESTING);
        switch (e.getCurrentItem().getType()) {
            case PLAYER_HEAD:
                ItemStack stack = e.getCurrentItem();
                Player player_to_kill = Bukkit.getPlayer(((SkullMeta) stack).getDisplayName());
                player_to_kill.setHealth(0);
                break;
            default:
                break;
        }
    }
    public static ItemStack getPlayerSkull(String playerName, String displayName, ArrayList<String> lore){
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        assert skullMeta != null;
        skullMeta.setOwner(playerName);
        skullMeta.setDisplayName(displayName);
        skullMeta.setLore(lore);
        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }
    @Override
    public void setMenuItems() {
       List<ItemStack> stack = new ArrayList<>();
       for(Player p : GameChat.GetAllPlayers()){
           ItemStack player_Head = getPlayerSkull(p.getName(),p.getDisplayName(),new ArrayList<String>(){{
               add(ChatColor.LIGHT_PURPLE + p.getDisplayName());
           }});

           stack.add(player_Head);
       }
       ItemStack[] it = new ItemStack[getSlots()];
       stack.toArray(it);
       inventory.setContents(it);
    }
}
