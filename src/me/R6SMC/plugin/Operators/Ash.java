package me.R6SMC.plugin.Operators;

import me.R6SMC.plugin.GameLogic;
import me.R6SMC.plugin.PlayerClass;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Ash extends Operator{
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
