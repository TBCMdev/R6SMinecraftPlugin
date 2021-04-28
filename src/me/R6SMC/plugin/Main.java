package me.R6SMC.plugin;
import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.CommandClasses.CommandCompleter;
import me.R6SMC.plugin.CommandClasses.CommandListener;
import me.R6SMC.plugin.CommandClasses.Commands;
import me.R6SMC.plugin.CommandClasses.CustomBookCommands;
import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Listeners.CameraListener;
import me.R6SMC.plugin.Listeners.MenuListener;
import me.R6SMC.plugin.Listeners.OperatorAbilityListener;
import org.bukkit.plugin.PluginManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.chat.TextComponent;

import java.util.*;


public class Main extends JavaPlugin implements Listener {
    public World world;
    public int
            players
            ,joinedPlayers
            ,RedTeamCount
            ,BlueTeamCount
            ,PickedOperators
            ,counterA = 0;

    public static ArrayList<String> PlayersWOP = new ArrayList<>();
    final Collection<? extends Player> PlayerList = Bukkit.getServer().getOnlinePlayers();

    public ArrayList<Player> RedTeam = new ArrayList<>();
    public ArrayList<Player> BlueTeam = new ArrayList<>();
    public ArrayList<Player> AlivePlayers = new ArrayList<>();
    public ArrayList<Object> PositionList = new ArrayList<Object>();
    public static Hashtable<String,Player> PlayerOperators = new Hashtable<>();


    public String ash = "";
    public String capitao = "";
    public String finka = "";
    public String doc = "";
    public String rook = "";
    public String tachanka = "";
    public ItemStack docAbil = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
    //ENDVARS
    @Override

    public void onEnable() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new CameraListener(),this);
        manager.registerEvents(new GameLogic(), this);
        manager.registerEvents(new MenuListener(),this);
        manager.registerEvents(new OperatorAbilityListener(),this);
        manager.registerEvents(new CameraListener(),this);
        Bukkit.getServer().getLogger().info("plugin `test plugin` is loaded and ready to use!");
        GameChat.BroadcastMessage("R6S MC loaded.");
        GameLogic.mainThread = this;
        world = Bukkit.getWorld("world");
        //ENABLE COMMANDS
        getCommand("tbcm-dc").setExecutor(new DevConsole());
        getCommand("tbcm-dc").setTabCompleter(new CommandCompleter());
        getCommand("showPageBlue").setExecutor(new CustomBookCommands());
        getCommand("showPageRed").setExecutor(new CustomBookCommands());
        for(String s : Commands.GetCommands(new String[]{"tbcm-dc","showPageBlue","showPageRed"}))//THESE ARE COMMANDS THAT DO NOT TAKE COMMAND LISTENER AS THEIR DEFAULT LISTENER
        {
            try{
                getCommand(s).setExecutor(new CommandListener());
            }catch (Exception e){
                DevConsole.BroadCastDevMessage("ERROR INITIALIZING COMMAND EXECUTOR(Command): " + s);
            }

        }
    }
    public static void GameStart(){
        GameLogic.HasGivenBooks = false;
        TextComponent defenseTxt = new TextComponent(ChatColor.BLUE + "*DEFENSE*");
        TextComponent hoverTextDEF = new TextComponent(ChatColor.BLUE +"CLICK TO JOIN THE DEFENDING TEAM");
        TextComponent attackTxt = new TextComponent(ChatColor.RED + "*ATTACK*");
        TextComponent hoverTextATTK = new TextComponent(ChatColor.RED + "CLICK TO JOIN THE ATTACKING TEAM");
        HoverEvent hoverEventDEF = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{hoverTextDEF});
        HoverEvent hoverEventATTK = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{hoverTextATTK});
        attackTxt.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/jointeamred"));
        defenseTxt.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/jointeamblue"));
        attackTxt.setHoverEvent(hoverEventATTK);
        defenseTxt.setHoverEvent(hoverEventDEF);
        Bukkit.getServer().spigot().broadcast(defenseTxt);
        Bukkit.getServer().spigot().broadcast(attackTxt);

    }
    public boolean onCommand(CommandSender sender,Command cmd,String CommandLabel,String[] args) {

        return true;
    }
}
