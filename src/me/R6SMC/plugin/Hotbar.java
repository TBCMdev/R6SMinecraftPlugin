package me.R6SMC.plugin;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Hotbar extends BukkitRunnable {
    public static Player p;
    public Hotbar(){

    }

    public void Organise(Player pl) {
        p = pl;
        this.runTaskTimer(GameLogic.mainThread,20 * 1,20);

    }
    public void run() {
        if(p != null) {
            if(p.getInventory().getHeldItemSlot() == 1) {

                p.getInventory().setHeldItemSlot(1);
                p.performCommand("function give:attachments/acog");

            }else {
                p.getInventory().setHeldItemSlot(1);
                Organise(p);
            }


        }
    }
}
