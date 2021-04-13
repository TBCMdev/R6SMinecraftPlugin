package me.R6SMC.plugin.Locations;

import me.R6SMC.plugin.DevConsole.DevConsole;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SpawnLocations {



    private static HashMap<String, Location> BlueSpawnLocations = new HashMap<String,Location>(){{}};
    private static HashMap<String, Location> RedSpawnLocations = new HashMap<String,Location>(){{}};



    public static Location getBlueLocation(int index, Player PlayerToSpawn){
        if(BlueSpawnLocations.values().toArray()[index] != null){
            return (Location)BlueSpawnLocations.values().toArray()[index];
        }else{
            DevConsole.SendDevMessage(PlayerToSpawn,"Could not retrieve the location for you.",DevConsole.TESTING);
        }

        return null;

    }public static Location getRedLocation(int index, Player PlayerToSpawn){
        if(RedSpawnLocations.values().toArray()[index] != null){
            return (Location)RedSpawnLocations.values().toArray()[index];
        }else{
            DevConsole.SendDevMessage(PlayerToSpawn,"Could not retrieve the location for you.",DevConsole.TESTING);
        }

        return null;

    }
}
