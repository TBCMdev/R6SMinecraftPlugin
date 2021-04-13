package me.R6SMC.plugin;
import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.CommandClasses.CommandListener;
import me.R6SMC.plugin.CommandClasses.Commands;
import me.R6SMC.plugin.CommandClasses.CustomBookCommands;
import me.R6SMC.plugin.DevConsole.DevConsole;
import me.R6SMC.plugin.GameLogic.GameLogic;
import me.R6SMC.plugin.Listeners.MenuListener;
import me.R6SMC.plugin.Operators.Operatorhandling.OperatorAbilityListener;
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
        manager.registerEvents(new GameLogic(), this);
        manager.registerEvents(new MenuListener(),this);
        manager.registerEvents(new OperatorAbilityListener(),this);
        Bukkit.getServer().getLogger().info("plugin `test plugin` is loaded and ready to use!");
        GameChat.BroadcastMessage("R6S MC loaded.");
        GameLogic.mainThread = this;
        world = Bukkit.getWorld("world");

        //ENABLE COMMANDS
        getCommand("tbcm-dc").setExecutor(new DevConsole());
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


    /*public void onHit(ProjectileHitEvent event,Block hitBlock,Projectile arrow) {
        Player p = (Player) arrow.getShooter();
        getLogger().info("you hit soomething");
        p.sendMessage("nice shot!");
        Entity entity = event.getEntity();

        Block block = event.getHitBlock();
        Location loc;

        if(shooter == ash) {
            if(block.getRelative(BlockFace.NORTH).getType().equals(Material.DARK_OAK_SLAB)) {
                Block relativeBlock = block.getRelative(BlockFace.NORTH);
                relativeBlock.setType(Material.AIR);
            }
            if(block.getRelative(BlockFace.SOUTH).getType().equals(Material.DARK_OAK_SLAB)) {
                Block relativeBlock = block.getRelative(BlockFace.SOUTH);
                relativeBlock.setType(Material.AIR);
            }
            if(block.getRelative(BlockFace.EAST).getType().equals(Material.DARK_OAK_SLAB)) {
                Block relativeBlock = block.getRelative(BlockFace.EAST);
                relativeBlock.setType(Material.AIR);
            }
            if(block.getRelative(BlockFace.WEST).getType().equals(Material.DARK_OAK_SLAB)){
                Block relativeBlock = block.getRelative(BlockFace.NORTH);
                relativeBlock.setType(Material.AIR);
            }

        }



    }*/



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


    //public void equipArmor3(Player p) {

    //if (p.getInventory().getArmorContents() != null) {
    //	ItemStack[] armor3 = { new ItemStack(Material.DIAMOND_BOOTS),
    //			new ItemStack(Material.DIAMOND_HELMET),
    //			new ItemStack(Material.DIAMOND_CHESTPLATE),
    //			new ItemStack(Material.DIAMOND_LEGGINGS), };

    //	p.getInventory().setArmorContents(armor3);
    //}
    //}
    public void PickOperator(String Name,CommandSender sender){
        Player player = (Player)sender;
        switch(Name.toLowerCase()) {
            case "doc":
                doc = sender.getName().toString();
                PlayerOperators.put(doc,(Player) sender);
                GameLogic.PickedOperators ++;
                GameLogic.CreatePlayerClass(player, player.getDisplayName(),"doc",PickedOperators,1);
                getLogger().info("player who has picked doc:" + doc);
                sender.sendMessage(ChatColor.GREEN + "you have chosen doc! to use his abilities, please refer to the action bar above your hotbar, when teleported into the game");
                GameChat.sendActionbar((Player) sender, ChatColor.BLUE + "click to activate docs healing effect");
                getLogger().info("operator picked.");
                PlayersWOP.add(sender.getName().toString());
                break;
            case "rook":
                rook = sender.getName().toString();
                PlayerOperators.put(rook,(Player) sender);
                GameLogic.PickedOperators ++;
                sender.sendMessage(ChatColor.GREEN + "you have chosen rook! to use his abilities, please refer to the action bar above your hotbar, when teleported into the game");
                GameLogic.CreatePlayerClass(player, player.getDisplayName(),"rook",PickedOperators,1);
                getLogger().info("operator picked");
                PlayersWOP.add(sender.getName().toString());
                break;
            case "tachanka":
                tachanka = sender.getName().toString();
                PlayerOperators.put(tachanka,(Player) sender);
                GameLogic.PickedOperators ++;
                sender.sendMessage(ChatColor.GREEN + "you have chosen tachanka! to use his abilities, please refer to the action bar above your hotbar, when teleported into the game");
                GameLogic.CreatePlayerClass(player, player.getDisplayName(),"tachanka",PickedOperators,1);
                getLogger().info("operator picked");
                PlayersWOP.add(sender.getName().toString());
                break;
            default:
                sender.sendMessage(ChatColor.RED + "*ERROR* " + ChatColor.GRAY + " usage: /defoperator [doc / rook / tachanka]");
                break;
        }
        GameLogic.ResendTeleportRed();
        GameLogic.ResendTeleportBlue();
    }

    public boolean onCommand(CommandSender sender,Command cmd,String CommandLabel,String[] args) {
        //if(cmd.getName().equalsIgnoreCase("showpageblue")){
        //    GameLogic.ShowAvailableOperators();
        //}

        return true;
    }





}
