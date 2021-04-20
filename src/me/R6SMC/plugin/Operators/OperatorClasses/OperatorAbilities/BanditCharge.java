package me.R6SMC.plugin.Operators.OperatorClasses.OperatorAbilities;

import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Operators.OperatorClasses.Defense.Bandit;
import me.R6SMC.plugin.Operators.Operatorhandling.Operator;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorUtility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Silverfish;
import org.bukkit.inventory.ItemStack;

public class BanditCharge {
    private static Material type;
    private OperatorUtility PlayerManager;
    private Bandit Owner;
    private Location[] DoorToElectrify;
    private Location position;
    private ItemStack ItemCharge;
    private Silverfish EntityCharge;
    private int BanditChargeIndex;
    public BanditCharge(OperatorUtility ou, Bandit owner, Location[] doorToElectrify,Location pos,int Counter){
        PlayerManager = ou;
        Owner = owner;
        DoorToElectrify = doorToElectrify;
        position = pos;
        BanditChargeIndex = Counter;
        Place();
    }
    public Silverfish getEntityCharge(){
        return EntityCharge;
    }
    public void Destroy(){
        EntityCharge.remove();
    }
    public void Place(){
        if(EntityCharge == null){
            ItemCharge = new ItemStack(type,1);
            Silverfish Charge = (Silverfish) GameLogic.world.spawnEntity(position, EntityType.SILVERFISH);
            Charge.setAI(false);
            Charge.setSilent(true);
            Charge.setInvisible(true);
            Charge.setInvulnerable(true);
            Charge.setHealth(1);
            Charge.setCustomName("BANDIT CHARGE " + BanditChargeIndex);
            Charge.setCustomNameVisible(true);
            EntityCharge = Charge;
            Owner.PlayerManager.getOwner().sendMessage(ChatColor.YELLOW + "you placed a bandit charge!");
            GameLogic.world.getBlockAt(position).setType(Material.CHORUS_FRUIT);//make texture for bandit charge
            Block data = GameLogic.world.getBlockAt(position);


        }

    }
}
