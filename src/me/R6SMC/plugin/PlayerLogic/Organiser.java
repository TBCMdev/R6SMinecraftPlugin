package me.R6SMC.plugin.PlayerLogic;

import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Organiser{
    public static String[] AllPrimaryGuns = new String[]{"MP5A3","M4A1","SVD","M500","M16A4","G3A3","SPAS 12","FN_FAL"};
    public static String[] AllSecondaryGuns = new String[]{"G17","1911","DEAGLE","MAC 10","PM","GLOCK 18"};
    public static void Organise(Player p,boolean KeepAmmo){
        new BukkitRunnable() {
            @Override
            public void run() {
                Inventory inv = p.getInventory();
                p.sendMessage(inv.getContents().toString());
                for (ItemStack i : inv) {
                    if (i.getType() == Material.AIR) {
                        DevConsole.SendDevMessage(p, "Returned as item is null!", DevConsole.TESTING);
                        return;
                    }
                    for(String s : AllPrimaryGuns){
                        if(i.getItemMeta().getLore().get(i.getItemMeta().getLore().size() - 1).equalsIgnoreCase(s)){
                            p.sendMessage("found primary gun");
                        }

                    }
                    p.sendMessage("getting item:" + i.getItemMeta());
                    if (i.getType() == Material.CARROT_ON_A_STICK) {
                        p.sendMessage("found weapon!");
                        ItemMeta Meta = i.getItemMeta();
                        String s = Meta.getDisplayName();
                        DevConsole.SendDevMessage(p, "iterating through inv", DevConsole.TESTING);
                        for (String PrimaryCheck : AllPrimaryGuns) {
                            if (s.equalsIgnoreCase(PrimaryCheck.toUpperCase())) {
                                p.performCommand("function mgs_catalog:attachments/acog");
                                p.getInventory().setItemInMainHand(i);
                            }
                        }
                        for (String SecondaryCheck : AllSecondaryGuns) {
                            if (s.equalsIgnoreCase(SecondaryCheck.toUpperCase())) {
                                p.getInventory().setHeldItemSlot(1);
                                p.getInventory().setItemInMainHand(i);
                            }
                        }

                    }
                    if (KeepAmmo) {
                        List<ItemStack> Mags = new ArrayList<>();
                        if (i.getType() == Material.CLOCK) {
                            ItemMeta Meta = i.getItemMeta();
                            for (String s : Meta.getLore()) {
                                if (s.contains("Ammuntion")) {
                                    //BY NOW we should know that it is a gun mag judging on its type and LORE.
                                    //ADD it to a list and when we are finished looping through the inventory, we will set the contents of the players storage inventory to all the mags.
                                    Mags.add(i);
                                }
                            }
                        }
                        p.getInventory().setStorageContents(Mags.toArray(new ItemStack[Mags.size()]));
                        //NOW move it to the back of your inventory and out of your hotbar.
                    } else {
                        //REMOVE ALL AMMO OR JUST RETURN OUT OF METHOD
                        DevConsole.SendDevMessage(p, "Inventory has been successfully organised.", DevConsole.GetTester());
                        return;
                    }
                }
            }
        }.runTaskLater(GameLogic.mainThread,30);
    }
}
