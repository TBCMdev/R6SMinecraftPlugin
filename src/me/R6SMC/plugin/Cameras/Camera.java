package me.R6SMC.plugin.Cameras;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
@Deprecated
public class Camera {
    private float RotXAllowance;
    private float RotYAllowance;
    private HashMap<Integer, Location> AllCams = new HashMap<Integer,Location>(){{

    }};
    private HashMap<Player, CameraUtility> AllPlayerCams = new HashMap<>();
    public void getOffCamera(Player p){
        if(AllPlayerCams.containsKey(p)){
            AllPlayerCams.remove(p);
        }
    }
    public void accessCamera(Player p,CameraUtility cu){
        if(!AllPlayerCams.containsKey(p)){
            AllPlayerCams.put(p,cu);
        }
    }
}
