package me.R6SMC.plugin.Listeners;

import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Ash;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Capitao;
import me.R6SMC.plugin.Operators.OperatorClasses.Attack.Finka;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.*;
import me.R6SMC.plugin.Operators.Operatorhandling.CurrentOperators;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class OperatorAbilityListener implements Listener {
    @EventHandler
    public void OnAbil(ProjectileHitEvent event, Entity hitEntity, Block hitBlock, BlockFace hitFace){
        if(event.getEntity() == null) return;
        Entity e = event.getEntity();
        if(e instanceof Snowball) {
            if (e.getCustomName().equalsIgnoreCase("capitaoabil")) {
                Snowball abil = (Snowball) e;
                World world = GameLogic.world;
                Capitao cap = (Capitao) CurrentOperators.CurrentOperators.get(abil.getShooter());
                world.createExplosion(e.getLocation(), cap.FireIntensity, true, false);
                DevConsole.SendDevMessage(cap.PlayerManager.getOwner(), "Explosion Created.", DevConsole.TESTING);
            }
        }
        if(e instanceof Arrow) {
            if (e.getCustomName().equalsIgnoreCase("ashabil")) {
                Arrow abil = (Arrow) e;
                World world = GameLogic.world;
                Ash ash = (Ash) CurrentOperators.CurrentOperators.get(abil.getShooter());
                Block HitBlock = event.getHitBlock();

            }
        }

    }
    @EventHandler
    public void OnAbilityHold(PlayerItemHeldEvent event){
        if(GameLogic.GameStarted) {
            try {
                @Nullable Material PlayerItemType = event.getPlayer().getInventory().getItem(event.getNewSlot()).getType();
                if (PlayerItemType == Material.RED_STAINED_GLASS_PANE) {
                    DevConsole.SendDevMessage(event.getPlayer(), "holding pane", DevConsole.TESTING);

                    try {
                        if (CurrentOperators.Check(event.getPlayer(), 3)) {
                            Aruni aruniOP = (Aruni) CurrentOperators.CurrentOperators.get(event.getPlayer());
                            aruniOP.HoldAbility();
                        }
                    } catch (Exception e) {
                        DevConsole.SendDevMessage(event.getPlayer(), "Could not Activate Arunis Hold Ability Function", DevConsole.TESTING);
                    }
                }
            }catch (Exception e){

            }
            if(event.getPlayer().getInventory().getItem(event.getNewSlot()) == null){
                DevConsole.SendDevMessage(event.getPlayer(),"not holding pane",DevConsole.TESTING);
                if(CurrentOperators.Check(event.getPlayer(),3)){
                    Aruni aruniOP = (Aruni) CurrentOperators.CurrentOperators.get(event.getPlayer());
                    aruniOP.CancelHoldAbility();
                }
            }
        }

    }
    @EventHandler
    public void PlayerMBclick(PlayerInteractEvent event) {
        Material PlayerItemType = event.getPlayer().getInventory().getItemInMainHand().getType();
        if(GameLogic.GameStarted) {
            if(event.getPlayer().getInventory().getItemInMainHand() == null) return;
            if(PlayerItemType == Material.RED_STAINED_GLASS_PANE){
                    if(CurrentOperators.Check(event.getPlayer(),3)){
                        Aruni aruniOP = (Aruni) CurrentOperators.CurrentOperators.get(event.getPlayer());
                        aruniOP.activateAbility();
                        //NOTE FOR SELF:Testing to see if this code can return the doc class and call ability, mostly unlikely so change if it does not work as intended.
                    }
            }
            if (PlayerItemType == Material.WHITE_STAINED_GLASS_PANE) {
                try {
                    if(CurrentOperators.Check(event.getPlayer(),1)){
                        Doc docOP = (Doc)CurrentOperators.CurrentOperators.get(event.getPlayer());
                        docOP.activateAbility();
                        //NOTE FOR SELF:Testing to see if this code can return the doc class and call ability, mostly unlikely so change if it does not work as intended.
                    }
                }catch (Exception e){
                    DevConsole.SendDevMessage(event.getPlayer(),"Could not instantiate class.",DevConsole.TESTING);
                }

            }
            if (PlayerItemType == Material.BROWN_STAINED_GLASS_PANE) {
                if (CurrentOperators.Check(event.getPlayer(), 5)) {
                    try {
                        Finka FinkaOP = (Finka) CurrentOperators.CurrentOperators.get(event.getPlayer());
                        FinkaOP.activateAbility();
                        //NOTE FOR SELF:Testing to see if this code can return the doc class and call ability, mostly unlikely so change if it does not work as intended.
                    }catch(Exception e){
                        DevConsole.SendDevMessage(event.getPlayer(),"Could not instantiate class.",DevConsole.TESTING);

                    }
                }
            }
            if(PlayerItemType == Material.GREEN_STAINED_GLASS_PANE){
                try {
                    Ash ash = (Ash) CurrentOperators.CurrentOperators.get(event.getPlayer());
                    ash.activateAbility();
                    //NOTE FOR SELF:Testing to see if this code can return the doc class and call ability, mostly unlikely so change if it does not work as intended.
                }catch(Exception e){
                    DevConsole.SendDevMessage(event.getPlayer(),"Could not instantiate class.",DevConsole.TESTING);

                }
            }
            if(PlayerItemType == Material.BLUE_STAINED_GLASS_PANE){
                try {
                    Rook rook = (Rook) CurrentOperators.CurrentOperators.get(event.getPlayer());
                    rook.activateAbility();
                    //NOTE FOR SELF:Testing to see if this code can return the doc class and call ability, mostly unlikely so change if it does not work as intended.
                }catch(Exception e){
                    DevConsole.SendDevMessage(event.getPlayer(),"Could not instantiate class.",DevConsole.TESTING);

                }
            }
            if(PlayerItemType == Material.GRAY_STAINED_GLASS_PANE) {
                try {
                    Capitao cap = (Capitao) CurrentOperators.CurrentOperators.get(event.getPlayer());
                    cap.activateAbility();
                    //NOTE FOR SELF:Testing to see if this code can return the doc class and call ability, mostly unlikely so change if it does not work as intended.
                } catch (Exception e) {
                    DevConsole.SendDevMessage(event.getPlayer(), "Could not instantiate class.", DevConsole.TESTING);

                }
}
        }
    }
    @EventHandler
    public void AbilDestroy(EntityDeathEvent e){
        if(e.getEntity().getType() == EntityType.SILVERFISH){
            if(GameLogic.GameStarted){
                Silverfish charge = (Silverfish) e.getEntity();
                if(charge.getCustomName().contains("BANDIT CHARGE ")){
                    Player killer = e.getEntity().getKiller();
                    try {
                        int index = Integer.parseInt(charge.getCustomName().split(" ")[1]);
                        Bandit bandit = (Bandit)CurrentOperators.CurrentOperators.get(killer);
                        bandit.CurrentCharges.get(index).Destroy();
                    }catch (Exception ex){
                        killer.sendMessage(ChatColor.RED + "could not remove the bandit charge.");
                    }
                }
            }
        }
        if(e.getEntity().getType().equals(EntityType.CAT)){
            if(GameLogic.GameStarted){
                Cat banshii = (Cat) e.getEntity();
                if(banshii.getCustomName().equalsIgnoreCase("banshii")){
                    Player killer = e.getEntity().getKiller();
                    GameLogic.PlayerClasses.get(killer.getDisplayName()).SetPoints(10);
                    Melusi m = (Melusi)CurrentOperators.CurrentOperators.get(Operator.OperatorStorage.get("melusi"));
                    m.getBanshiiByLoc(e.getEntity().getLocation()).Destroy();
                }
            }
        }
    }
    @EventHandler
    public void AbilHurt(EntityDamageByEntityEvent e){
        if(GameLogic.GameStarted){
            try{
                Player damager = (Player)e.getDamager();
                if(GameLogic.BlueTeam.contains(damager)) {
                    damager.sendMessage(ChatColor.DARK_PURPLE + "please try not to break team gadgets!");
                    e.setDamage(0);
                    e.setCancelled(true);
                }
            }catch (Exception ex){

            }
        }
    }
}
