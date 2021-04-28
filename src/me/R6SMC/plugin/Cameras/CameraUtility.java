package me.R6SMC.plugin.Cameras;

import javafx.util.Pair;
import me.R6SMC.plugin.Errors;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
public class CameraUtility {
    private int index;
    private int team;
    private float DefaultXRot;
    private float DefaultYRot;
    private float RotXMax;
    private float RotYMax;
    private Location CamPos;
    private Block camera;
    public CameraUtility(int index,int team,float RotYmax,float DefX,float DefY){
        this.index = index;
        this.team = team;
        RotYMax = RotYmax;
        DefaultXRot = DefX;
        DefaultYRot = DefY;
    }
    public void initCam(Location l){
        Block b = l.getBlock();
        camera = b;
        CamPos = l;
        b.setType(Material.CHORUS_PLANT);

    }
    public Location getLocation(){
        return CamPos;
    }
    public Pair<Float,Float> getRots(){
        return new Pair<>(RotXMax,RotYMax);
    }
    public Pair<Float,Float> getDefRots(){
        return new Pair<>(DefaultXRot,DefaultYRot);
    }
    public int getIndex(){
        return this.index;
    }
    public int getTeam(){
        return this.team;
    }
}
