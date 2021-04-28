package me.R6SMC.plugin.Loadouts;

import javafx.util.Pair;
import me.R6SMC.plugin.PlayerLogic.Organiser;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Guns {

    private static HashMap<String, Pair<String[],String[]>> attachments = new HashMap<String,Pair<String[],String[]>>(){{
        List<String> names = new ArrayList<>();
        for(String s : Organiser.AllPrimaryGuns){
            names.add(s);
        }

    }};/*<String,Pair<String[],String> = "name",<"available attachments","commands for sed attachment">*/
}
