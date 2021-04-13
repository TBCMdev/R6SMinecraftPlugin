package me.R6SMC.plugin.Operators.OperatorClasses;

import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.meta.ItemMeta;

public class Ash extends Operator {
    public Ash(OperatorUtility ou) {
        super(3,ou.getOwner(),ou.getOwnerClass(),"ash");
    }

    @Override
    public void ResetAbility() {

    }

    @Override
    public void SetAbility() {

    }

    @Override
    public void activateAbility() {
        String name = "ASHABIL";
        Snowball ball = GameLogic.world.spawn(player.getEyeLocation(),Snowball.class);
        ItemMeta meta = ball.getItem().getItemMeta();
        meta.setDisplayName(name);
    }
}
