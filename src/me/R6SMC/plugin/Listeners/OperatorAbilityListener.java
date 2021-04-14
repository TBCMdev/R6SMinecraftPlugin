package me.R6SMC.plugin.Listeners;

import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.OperatorClasses.Ash;
import me.R6SMC.plugin.Operators.OperatorClasses.Capitao;
import me.R6SMC.plugin.Operators.OperatorClasses.Doc;
import me.R6SMC.plugin.Operators.OperatorClasses.Finka;
import me.R6SMC.plugin.Operators.Operatorhandling.CurrentOperators;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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
    public void PlayerMBclick(PlayerInteractEvent event) {
        if(GameLogic.GameStarted) {
            if(event.getPlayer().getInventory().getItemInMainHand() == null) return;
            if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.WHITE_STAINED_GLASS_PANE) {
                try {
                    if(CurrentOperators.Check(event.getPlayer(),1)){
                        Doc docOP = (Doc)CurrentOperators.CurrentOperators.get(event.getPlayer());
                        docOP.activateAbility();
                        //NOTE FOR SELF:Testing to see if this code can return the doc class and call ability, mostly unlikely so change if it does not work as intended.
                    }
                }catch (Exception e){

                }

            }
            if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.BROWN_STAINED_GLASS_PANE) {
                if (CurrentOperators.Check(event.getPlayer(), 5)) {
                    Finka FinkaOP = (Finka) CurrentOperators.CurrentOperators.get(event.getPlayer());
                    FinkaOP.activateAbility();
                    //NOTE FOR SELF:Testing to see if this code can return the doc class and call ability, mostly unlikely so change if it does not work as intended.
                }
            }
        }
    }

}
