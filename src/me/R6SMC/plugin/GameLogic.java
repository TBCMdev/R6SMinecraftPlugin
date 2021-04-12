package me.R6SMC.plugin;

import me.R6SMC.plugin.Operators.Doc;
import me.R6SMC.plugin.Operators.Operator;
import me.R6SMC.plugin.Operators.OperatorHolder;
import me.R6SMC.plugin.menu.PickDefenseOperatorMenu;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameLogic implements Listener
{
    public static boolean PlayersCanMove = true;
    public static Main mainThread;
    public static World world = Bukkit.getWorld("world");
    public static Location DefaultPos = new Location(world,740,30,-678,45.5f,26.5f);
    public static BossBar B = Bukkit.createBossBar("TIME LEFT", BarColor.WHITE, BarStyle.SOLID);
    public static List<Player> BlueTeam = new ArrayList<>();
    public static List<Player> RedTeam = new ArrayList<>();
    public static List<Player> DeadPlayers = new ArrayList<>();
    public static List<Player> AlivePlayers = new ArrayList<>();
    public static Map<String,PlayerClass> PlayerClasses = new HashMap<>();
    public static String ash = "";
    public static String capitao = "";
    public static String finka = "";
    public static String doc = "";
    public static String rook = "";
    public static String tachanka = "";

    public static ArrayList<Object> PositionList = new ArrayList<Object>();

    public static ItemStack docAbil = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
    public static ItemStack rookAbil = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
    public static ItemStack tachankaAbil = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    public static ItemStack ashAbil = new ItemStack(Material.BROWN_STAINED_GLASS_PANE);
    public static ItemStack finkaAbil = new ItemStack(Material.BROWN_STAINED_GLASS_PANE);
    public static ItemStack capitaoAbil = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

    public static int
            players
            ,joinedPlayers
            ,RedTeamCount
            ,BlueTeamCount
            ,PickedOperators
            ,counterA = 0;
    public static float GameTime = 1;

    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event){
        if(!PlayersCanMove){
            event.getPlayer().teleport(new Location(world,event.getFrom().getBlockX(),event.getFrom().getBlockY(),event.getFrom().getBlockZ(),45.5f,26.5f));
        }
    }
    @EventHandler
    public void PlayerMBclick(PlayerInteractEvent event) {


        if(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.WHITE_STAINED_GLASS_PANE) {
            Doc doc = new Doc(OperatorHolder.GetOperator(event.getPlayer()));
            doc.activateAbility();



        }
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)/*HasItem*/) {
            //brokeRight = true;
        }
    }
    public static void TryEndGame(Player p){
        if(BlueTeam.contains(p)) {
            BlueTeam.remove(p);
            DeadPlayers.add(p);
            p.setGameMode(GameMode.SPECTATOR);
            if(AlivePlayers.size() >= 2 && RedTeam.size() == BlueTeam.size() ||
                    AlivePlayers.size() > 2 && RedTeam.size() > BlueTeam.size() ||
                    AlivePlayers.size() > 2 && RedTeam.size() < BlueTeam.size()) {

                int randPlayer = 0 + (int)(Math.random() * (BlueTeam.size() - 0));
                p.setSpectatorTarget(BlueTeam.get(randPlayer));
            }else {
                EndGame();
            }
        }
        if(RedTeam.contains(p)) {
            RedTeam.remove(p);
            DeadPlayers.add(p);
            p.setGameMode(GameMode.SPECTATOR);
            if(AlivePlayers.size() >= 2 && RedTeam.size() == BlueTeam.size() ||
                    AlivePlayers.size() > 2 && RedTeam.size() > BlueTeam.size() ||
                    AlivePlayers.size() > 2 && RedTeam.size() < BlueTeam.size()) {

                int randPlayer = 0 + (int)(Math.random() * (RedTeam.size() - 0));
                p.setSpectatorTarget(RedTeam.get(randPlayer));
            }else {
                EndGame();
            }
        }

    }
    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        Bukkit.getLogger().info("Player Has Died");
        Player Killed = event.getEntity();
        Player Killer = event.getEntity().getKiller();
        if(event.getEntityType() == EntityType.PLAYER) {
            Killed.sendMessage(ChatColor.DARK_RED + "DEATHS " + ChatColor.WHITE + "+1");
            TryEndGame(Killed);
        }
        if(event.getEntity().getKiller().getType() == EntityType.PLAYER) {
            if(GameChat.GetPlayerClass(Killer).Team == GameChat.GetPlayerClass(Killed).Team) {
                Killer.sendMessage(ChatColor.DARK_RED + "DO NOT TEAM KILL!! -100 POINTS");
                PlayerClasses.get(Killer.getDisplayName()).SetPoints(-100);
            }else{
                Killer.sendMessage(GameChat.GetTeamColor(PlayerClasses.get(Killer.getDisplayName())) + "KILLS " + ChatColor.WHITE + "+1");
                PlayerClasses.get(Killer.getDisplayName()).SetPoints(100);
            }
        }

    }
    public static void PlayerJoinedTeam(int Team){

    }
    public static void EndGame(){
        B.removeAll();
        PlayersCanMove = false;
        for(Player p : GameChat.GetAllPlayers()){
            p.setGameMode(GameMode.SPECTATOR);
            p.teleport(new Location(world,740,30,-678,45.5f,26.5f));

        }
    }
    public static void TeleportPlayersBLUE(int team) {
    for (Player EachPlayer : BlueTeam) {
        AlivePlayers.add(EachPlayer);
        Bukkit.getLogger().info("player found with name of:" + EachPlayer.getName().toString());
        players++;
        boolean TeamEqual = true;
        if (doc.equals(EachPlayer.getName().toString())) {
            Bukkit.getLogger().info("giving player specifed ability item");
            docAbil.getItemMeta().setDisplayName("ability");
            EachPlayer.getInventory().addItem(docAbil);


        }
        if (EachPlayer.getDisplayName().toString().equals(rook)) {
            Bukkit.getLogger().info("giving player specifed ability item");

        }
        if (EachPlayer.getDisplayName().toString().equals(tachanka)) {
            Bukkit.getLogger().info("giving player specifed ability item");

        }

        //CHECK FOR UNEVEN TEAMS


        Location defaultspawndef = new Location(world, 723, 15, -662, 0f, 1.2f);
        PickDefenseOperatorMenu defMenu = new PickDefenseOperatorMenu(PlayerMenus.GetPlayerMenuUtility(EachPlayer));
        defMenu.Open();
        PositionList.add(defaultspawndef);
        Bukkit.getLogger().info("blue team: " + EachPlayer);
        EachPlayer.teleport(defaultspawndef);

        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(console, "test");
        Bukkit.broadcastMessage(ChatColor.RED + "The Game Has Begun!");
        StartTimer();
        StartGame();
    }
}
    public static void JoinTeamRed(Player p) {
        if(RedTeamCount < BlueTeamCount || RedTeamCount == 0 && BlueTeamCount == 0) {
            RedTeam.add(p);
            joinedPlayers ++;
            RedTeamCount ++;
        }
        String[] AttackOPTextContents = new String[]{"*ASH*","*CAPITAO*","*FINKA*"};
        String[] AttackHoverContents = new String[]{"CLICK TO USE ASH","CLICK TO USE CAPITAO","CLICK TO USE FINKA"};
        String[] AttackClickCommands = new String[]{"/attkoperator ash","/attkoperator capitao","/attkoperator finka"};

        TextComponent[] AttackOPTexts = GameChat.CreateTextComponents(new TextComponent[2],AttackOPTextContents,ChatColor.RED);

        GameChat.CreateHoverEvents(HoverEvent.Action.SHOW_TEXT, AttackOPTexts, AttackHoverContents);
        GameChat.CreateClickEvents(ClickEvent.Action.RUN_COMMAND,AttackOPTexts,AttackClickCommands);

        GameChat.SendAllTextEvents(AttackOPTexts,p);
        //GIVE THE BOOK
        //SET POSITIONS AND DISALLOW MOVEMENT


        int team = 2;//ATTACK TEAM IS VALUED AT 2
        GameChat.SendMessage(ChatColor.DARK_RED + "you joined the attacking team!",p);


        if(joinedPlayers >= GameChat.GetAllPlayers().size() && PickedOperators >= joinedPlayers) {
            Bukkit.getLogger().info("players: " + GameChat.GetAllPlayers().size());

            //DEPRECATED CODE REMOVED FOR TESTING
            TeleportPlayersRED(team);
        }

    }
    public static void TeleportPlayersRED(int team) {

        for(Player EachPlayer : RedTeam) {
            AlivePlayers.add(EachPlayer);
            Bukkit.getLogger().info("player found with name of:" + EachPlayer.getName().toString());
            players ++;
            boolean TeamEqual = true;
            //CHECK FOR UNEVEN TEAMS
            if(ash == EachPlayer.getName().toString()) {
                Bukkit.getLogger().info("giving player specifed ability item");
                EachPlayer.getInventory().addItem(ashAbil);

            }
            if(capitao == EachPlayer.getName().toString()) {
                Bukkit.getLogger().info("giving player specifed ability item");

            }
            if(finka == EachPlayer.getName().toString()) {
                Bukkit.getLogger().info("giving player specifed ability item");
                EachPlayer.getInventory().addItem(finkaAbil);

            }

            Location defaultspawn = new Location(world,767,10,-628,90f,3f);


            PositionList.add(defaultspawn);

            Bukkit.getLogger().info("red team: " + EachPlayer);
            EachPlayer.teleport(defaultspawn);



        }
    }
    public static void ResendTeleportRed() {
        if(joinedPlayers >= GameChat.GetAllPlayers().size() && PickedOperators >= GameChat.GetAllPlayers().size()) {
            Bukkit.getLogger().info("players: " + GameChat.GetAllPlayers().size());
            TeleportPlayersRED(2);
        }
    }
    public static void ResendTeleportBlue() {
        if(joinedPlayers >= GameChat.GetAllPlayers().size() && PickedOperators >= GameChat.GetAllPlayers().size()) {
            Bukkit.getLogger().info("players: " + GameChat.GetAllPlayers().size());
            TeleportPlayersBLUE(1);
        }
    }
    public static void JoinTeamBlue(Player p) {
        if(RedTeamCount > BlueTeamCount || RedTeamCount == 0 && BlueTeamCount == 0) {
            BlueTeam.add(p);
            joinedPlayers++;
            Bukkit.getLogger().info("playeradded!!!");
        }

        TextComponent defenseTxtrook = new TextComponent(ChatColor.BLUE + "*ROOK*");
        TextComponent hoverTextrook = new TextComponent(ChatColor.BLUE +"CLICK TO USE ROOK");

        TextComponent defenseTxtdoc = new TextComponent(ChatColor.BLUE + "*DOC*");
        TextComponent hoverTextdoc = new TextComponent(ChatColor.BLUE +"CLICK TO USE DOC");

        TextComponent defenseTxtT = new TextComponent(ChatColor.BLUE + "*TACHANKA*");
        TextComponent hoverTextT = new TextComponent(ChatColor.BLUE +"CLICK TO USE TACHANKA");

        HoverEvent hoverEventrook = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{hoverTextrook});
        HoverEvent hoverEventdoc = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{hoverTextdoc});
        HoverEvent hoverEventT = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{hoverTextT});

        defenseTxtrook.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/defoperator rook"));
        defenseTxtdoc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/defoperator doc"));
        defenseTxtT.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/defoperator tachanka"));

        defenseTxtrook.setHoverEvent(hoverEventrook);
        defenseTxtdoc.setHoverEvent(hoverEventdoc);
        defenseTxtT.setHoverEvent(hoverEventT);

        p.spigot().sendMessage(defenseTxtrook);
        p.spigot().sendMessage(defenseTxtdoc);
        p.spigot().sendMessage(defenseTxtT);


        int team = 1;//DEFENSE TEAM IS VALUED AT 1
        p.sendMessage("you joined the defending team!");

        Bukkit.getLogger().info("players in server:" + GameChat.GetAllPlayers());
        if(joinedPlayers >= GameChat.GetAllPlayers().size() && PickedOperators >= joinedPlayers) {
            Bukkit.getLogger().info("players: " + GameChat.GetAllPlayers().size() + "\n operators picked: " + PickedOperators);
            TeleportPlayersBLUE(team);
            /*for(Player pl : GameChat.GetAllPlayers()){
                pl.teleport(GameLogic.DefaultPos);
                pl.setGameMode(GameMode.SPECTATOR);
                GameLogic.PlayersCanMove = false;
            }
            ItemStack OperatorChooseBookBlue = CustomBooks.CreateBlueTeamBook();
            for(Player pl : GameLogic.BlueTeam){
                pl.getInventory().setItemInMainHand(OperatorChooseBookBlue);
                pl.openBook(pl.getInventory().getItemInMainHand());
            }
            ItemStack OperatorChooseBookRed = CustomBooks.CreateRedTeamBook();
            for(Player pl : GameLogic.RedTeam){
                pl.getInventory().setItemInMainHand(OperatorChooseBookRed);
                pl.openBook(pl.getInventory().getItemInMainHand());
            }*/
        }
    }
    public static boolean HasGivenBooks = false;
    public static void ShowAvailableOperators(){
        if(!HasGivenBooks){
            ItemStack BlueTeamBook = CustomBooks.CreateBlueTeamBook();
            ItemStack RedTeamBook = CustomBooks.CreateRedTeamBook();
            for(Player p : BlueTeam){
                p.getInventory().setItemInMainHand(BlueTeamBook);
            }
            for(Player p : RedTeam) {
                p.getInventory().setItemInMainHand(RedTeamBook);
            }

        }else{
            return;
        }
}
public static void StartGame(){
        for(Player p : GameChat.GetAllPlayers()){
            p.setGameMode(GameMode.ADVENTURE);
        }
}


public static long TimerDelay = 0,
                   TimerPeriod = 25;
public static void StartTimer(){
    PlayersCanMove = true;
    Timer timer = new Timer(TimerDelay,TimerPeriod);
    timer.startTimer();
}
    public static boolean GameStarted = false;

    public static void Organise(Player p){

    }

    public static void CreatePlayerClass(Player p , String Name, String Class,int ID,int Team){
        if(!PlayerClasses.containsKey(Name)){
            PlayerClasses.put(Name,new PlayerClass(p,Name,Class,ID,Team));
            Bukkit.getLogger().info(PlayerClasses.toString());
            Bukkit.getLogger().info("CreatedPlayerClass");
        }
    }
}

