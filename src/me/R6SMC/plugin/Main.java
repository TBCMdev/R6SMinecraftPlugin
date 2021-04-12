package me.R6SMC.plugin;
import me.R6SMC.plugin.Listeners.MenuListener;
import org.bukkit.plugin.PluginManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.chat.TextComponent;

import java.util.*;
import java.util.regex.PatternSyntaxException;


public class Main extends JavaPlugin implements Listener {
    public boolean brokeLeft,brokeRight,HasItem = false;
    public World world;
    public static String[] OPERATORS = {"ash","capitao","finka","doc","rook","tachanka"};
    public int
            players
            ,joinedPlayers
            ,RedTeamCount
            ,BlueTeamCount
            ,PickedOperators
            ,counterA = 0;

    public static ArrayList<String> PlayersWOP = new ArrayList<>();
    public BossBar B = Bukkit.createBossBar("TIME LEFT", BarColor.WHITE, BarStyle.SOLID);
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
    public float GameTime = 1;
    public ItemStack docAbil = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
    public ItemStack rookAbil = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
    public ItemStack tachankaAbil = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    public ItemStack ashAbil = new ItemStack(Material.BROWN_STAINED_GLASS_PANE);
    public ItemStack finkaAbil = new ItemStack(Material.BROWN_STAINED_GLASS_PANE);
    public ItemStack capitaoAbil = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);




    //ENDVARS
    @Override

    public void onEnable() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new GameLogic(), this);
        manager.registerEvents(new MenuListener(),this);
        Bukkit.getServer().getLogger().info("plugin `test plugin` is loaded and ready to use!");
        GameChat.BroadcastMessage("R6S MC loaded.");
        GameLogic.mainThread = this;
        world = Bukkit.getWorld("world");

        //ENABLE COMMANDS
        getCommand("tbcm-dc").setExecutor(new DevConsole());
        getCommand("showPageBlue").setExecutor(new CustomBookCommands());
        getCommand("showPageRed").setExecutor(new CustomBookCommands());
    }



    public void JoinTeamRed(Player p) {
        if(RedTeamCount < BlueTeamCount || RedTeamCount == 0 && BlueTeamCount == 0) {
            RedTeam.add(p);
            GameLogic.RedTeam.add(p);
            joinedPlayers ++;
            RedTeamCount ++;
        }
        /*String[] AttackOPTextContents = new String[]{"*ASH*","*CAPITAO*","*FINKA*"};
        String[] AttackHoverContents = new String[]{"CLICK TO USE ASH","CLICK TO USE CAPITAO","CLICK TO USE FINKA"};
        String[] AttackClickCommands = new String[]{"/attkoperator ash","/attkoperator capitao","/attkoperator finka"};

        TextComponent[] AttackOPTexts = GameChat.CreateTextComponents(new TextComponent[2],AttackOPTextContents,ChatColor.RED);

        GameChat.CreateHoverEvents(HoverEvent.Action.SHOW_TEXT, AttackOPTexts, AttackHoverContents);
        GameChat.CreateClickEvents(ClickEvent.Action.RUN_COMMAND,AttackOPTexts,AttackClickCommands);

        GameChat.SendAllTextEvents(AttackOPTexts,p);
        //GIVE THE BOOK
        //SET POSITIONS AND DISALLOW MOVEMENT
        */

        int team = 2;//ATTACK TEAM IS VALUED AT 2
        GameChat.SendMessage(ChatColor.DARK_RED + "you joined the attacking team!",p);


        if(joinedPlayers >= PlayerList.size() && PickedOperators >= PlayerList.size()) {
            getLogger().info("players: " + PlayerList.size());

            //DEPRECATED CODE REMOVED FOR TESTING
            //TeleportPlayersRED(team);
        }

    }




    public boolean GameStarted = false;

    public void EndGame() {

    }

    void TeleportPlayersBLUE(int team) {
        for(Player EachPlayer : BlueTeam) {
            AlivePlayers.add(EachPlayer);
            getLogger().info("player found with name of:" + EachPlayer.getName().toString());
            players ++;
            boolean TeamEqual = true;
            if(doc == EachPlayer.getName().toString()) {
                getLogger().info("giving player specifed ability item");
                docAbil.getItemMeta().setDisplayName("ability");
                EachPlayer.getInventory().addItem(docAbil);


            }
            if(EachPlayer.getDisplayName().toString() == rook) {
                getLogger().info("giving player specifed ability item");

            }
            if(EachPlayer.getDisplayName().toString() == tachanka) {
                getLogger().info("giving player specifed ability item");

            }

            //CHECK FOR UNEVEN TEAMS



            Location defaultspawndef = new Location(world,723,15,-662,0f,1.2f);


            PositionList.add(defaultspawndef);
            getLogger().info("blue team: " + EachPlayer);
            EachPlayer.teleport(defaultspawndef);


            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(console, "test");
            Bukkit.broadcastMessage(ChatColor.RED + "The Game Has Begun!");

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



    void GameStart(Player p){
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
