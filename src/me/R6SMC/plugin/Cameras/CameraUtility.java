package me.R6SMC.plugin.Cameras;

import org.bukkit.entity.Player;
@Deprecated
public class CameraUtility {
    private int index;
    private int team;
    public CameraUtility(int index,int team){
        this.index = index;
        this.team = team;
    }

    public int getIndex(){
        return this.index;
    }
    public int getTeam(){
        return this.team;
    }
}
