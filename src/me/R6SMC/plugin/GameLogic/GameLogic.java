package me.R6SMC.plugin.GameLogic;

import me.R6SMC.plugin.*;
import me.R6SMC.plugin.Books.CustomBooks;
import me.R6SMC.plugin.Chat.GameChat;
import me.R6SMC.plugin.Loadouts.Loadouts;
import me.R6SMC.plugin.Operators.Operatorhandling.CurrentOperators;
import me.R6SMC.plugin.PlayerLogic.PlayerClass;
import me.R6SMC.plugin.menu.MenuInstances.PickAttackOperatorMenu;
import me.R6SMC.plugin.menu.MenuInstances.PickDefenseOperatorMenu;
import me.R6SMC.plugin.menu.PlayerMenus;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
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
    public static Map<String, PlayerClass> PlayerClasses = new HashMap<>();
    public static String ash = "";
    public static String capitao = "";
    public static String finka = "";
    public static String doc = "";
    public static String rook = "";
    public static String Aruni = "";

    public static ArrayList<Object> PositionList = new ArrayList<Object>();

    public static ItemStack docAbil = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
    public static ItemStack rookAbil = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
    public static ItemStack AruniAbil = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    public static ItemStack ashAbil = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
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
    public static boolean TryEndGame(Player p){
        if(AlivePlayers.contains(p)) {
            AlivePlayers.remove(p);
            DeadPlayers.add(p);
            p.setGameMode(GameMode.SPECTATOR);
            if(AlivePlayers.size() >= 2 && RedTeam.size() == BlueTeam.size() ||
                    AlivePlayers.size() > 2 && RedTeam.size() > BlueTeam.size() ||
                    AlivePlayers.size() > 2 && RedTeam.size() < BlueTeam.size()) {

                int randPlayer = 0 + (int)(Math.random() * (BlueTeam.size() - 0));
                p.setSpectatorTarget(BlueTeam.get(randPlayer));
                return false;
            }else {
                if(RedTeam.size() > 0 && BlueTeam.size() <= 0 && GameStarted) {
                    EndGame(2);
                    return true;
                }else if(BlueTeam.size() > 0 && RedTeam.size() <= 0 && GameStarted){
                    EndGame(1);
                    return true;
                }
            }
        }
        return false;
    }
    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        boolean setTarget = true;
        if(GameStarted) {
            Bukkit.getLogger().info("Player Has Died");
            Player Killed = event.getEntity();
            Player Killer = event.getEntity().getKiller();
            if (event.getEntityType() == EntityType.PLAYER) {
                Killed.sendMessage(ChatColor.DARK_RED + "DEATHS " + ChatColor.WHITE + "+1");
                if(TryEndGame(Killed)){
                    setTarget = false;
                }else{
                    setTarget = true;
                }
            }
            if (event.getEntity().getKiller().getType() == EntityType.PLAYER) {
                assert Killer != null;
                if (GameChat.GetPlayerClass(Killer).Team == GameChat.GetPlayerClass(Killed).Team) {
                    Killer.sendMessage(ChatColor.DARK_RED + "DO NOT TEAM KILL!! -100 POINTS");
                    PlayerClasses.get(Killer.getDisplayName()).SetPoints(-100);
                } else {
                    Killer.sendMessage(GameChat.GetTeamColor(PlayerClasses.get(Killer.getDisplayName())) + "KILLS " + ChatColor.WHITE + "+1");
                    PlayerClasses.get(Killer.getDisplayName()).SetPoints(100);
                    GameChat.sendActionbar(Killer,ChatColor.GOLD + "YOU KILLED " + ChatColor.ITALIC +  Killed.getDisplayName());

                }
            }
            if(setTarget) {
                Spectate.SetPlayerTarget(event.getEntity().getPlayer(), PlayerClasses.get(event.getEntity().getPlayer().getDisplayName()).GetTeam());
            }
            AlivePlayers.remove(event.getEntity().getPlayer());
        }
    }
    public static void EndGame(int WinningTeam){
        B.removeAll();
        //CHECK TO END GAME OR TO CONTINUE
        if(GameRounds.NewRound(WinningTeam)){
            return;
        }
        PlayersCanMove = false;
        List<Location> FinishedNpcLocations = new ArrayList<Location>(){{
            //PUT NPC POS AND ROT HERE
        }};

        for(Player p : GameChat.GetAllPlayers()){
            p.setGameMode(GameMode.SPECTATOR);
            p.teleport(new Location(world,740,30,-678,45.5f,26.5f));

        }
        PlayerEntities.CreateEntities(GameChat.GetAllPlayers(),FinishedNpcLocations);
    }
    public static void TeleportPlayersPickingOPS(){
        PlayersCanMove = false;
        for(Player p : GameChat.GetAllPlayers()){
            p.setGameMode(GameMode.SPECTATOR);
            p.teleport(new Location(world,740,30,-678,45.5f,26.5f));

        }
    }
    public static void InitTeamRed(Player p){
        if(RedTeamCount < BlueTeamCount || RedTeamCount == 0 && BlueTeamCount == 0) {
            RedTeam.add(p);
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
        GameChat.SendMessage(ChatColor.DARK_RED + "you joined the attacking team!",p);
        TeleportPlayersPickingOPS();
        PickAttackOperatorMenu menu = new PickAttackOperatorMenu(PlayerMenus.GetPlayerMenuUtility(p));
        menu.Open();
        if(joinedPlayers >= GameChat.GetAllPlayers().size() && PickedOperators >= joinedPlayers) {
            Bukkit.getLogger().info("players: " + GameChat.GetAllPlayers().size());

        }
    }
    public static void InitTeamBlue(Player p) {
        if (RedTeamCount > BlueTeamCount || RedTeamCount == 0 && BlueTeamCount == 0) {
            BlueTeam.add(p);
            joinedPlayers++;
            Bukkit.getLogger().info("playeradded!!!");
        }
        //deprecated as i am trying out the inventory window method for Choosing operators.

        /*TextComponent defenseTxtrook = new TextComponent(ChatColor.BLUE + "*ROOK*");
        TextComponent hoverTextrook = new TextComponent(ChatColor.BLUE + "CLICK TO USE ROOK");

        TextComponent defenseTxtdoc = new TextComponent(ChatColor.BLUE + "*DOC*");
        TextComponent hoverTextdoc = new TextComponent(ChatColor.BLUE + "CLICK TO USE DOC");

        TextComponent defenseTxtT = new TextComponent(ChatColor.BLUE + "*Aruni*");
        TextComponent hoverTextT = new TextComponent(ChatColor.BLUE + "CLICK TO USE Aruni");

        HoverEvent hoverEventrook = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{hoverTextrook});
        HoverEvent hoverEventdoc = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{hoverTextdoc});
        HoverEvent hoverEventT = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{hoverTextT});

        defenseTxtrook.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/defoperator rook"));
        defenseTxtdoc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/defoperator doc"));
        defenseTxtT.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/defoperator Aruni"));

        defenseTxtrook.setHoverEvent(hoverEventrook);
        defenseTxtdoc.setHoverEvent(hoverEventdoc);
        defenseTxtT.setHoverEvent(hoverEventT);

        p.spigot().sendMessage(defenseTxtrook);
        p.spigot().sendMessage(defenseTxtdoc);
        p.spigot().sendMessage(defenseTxtT);
        */
        TeleportPlayersPickingOPS();
        PickDefenseOperatorMenu defMenu = new PickDefenseOperatorMenu(PlayerMenus.GetPlayerMenuUtility(p));
        defMenu.Open();
        int team = 1;//DEFENSE TEAM IS VALUED AT 1
        p.sendMessage("you joined the defending team!");
    }
    public static void JoinTeam(int Team,Player p){
        switch (Team){
            case 1:
                InitTeamBlue(p);
                break;
            case 2:
                InitTeamRed(p);
                break;
        }
    }
    public static void GiveWindowsAfterInit(){
        for(Player p : BlueTeam){
            PickDefenseOperatorMenu defMenu = new PickDefenseOperatorMenu(PlayerMenus.GetPlayerMenuUtility(p));
            defMenu.Open();
        }
        for(Player p : RedTeam){
            PickAttackOperatorMenu attkMenu = new PickAttackOperatorMenu(PlayerMenus.GetPlayerMenuUtility(p));
            attkMenu.Open();
        }
    }
    public static void InitGame(){
        if(!GameStarted) {
            GameStarted = true;
            StartGame();
            StartTimer();
        }
    }
    public static void TeleportTeams(int team) {
        switch (team) {
            case 2:
                for (Player EachPlayer : RedTeam) {
                    AlivePlayers.add(EachPlayer);
                    Bukkit.getLogger().info("player found with name of:" + EachPlayer.getName().toString());
                    players++;
                    boolean TeamEqual = true;
                    //CHECK FOR UNEVEN TEAMS
                    if (CurrentOperators.Check(EachPlayer, 4)) {
                        Loadouts.GiveAbilityItem(4, EachPlayer);
                    }
                    if (CurrentOperators.Check(EachPlayer, 5)) {
                        Loadouts.GiveAbilityItem(5, EachPlayer);
                    }
                    if (CurrentOperators.Check(EachPlayer, 6)) {
                        Loadouts.GiveAbilityItem(6, EachPlayer);

                        Location defaultspawn = new Location(world, 767, 10, -628, 90f, 3f);

                        PositionList.add(defaultspawn);
                        Bukkit.getLogger().info("red team: " + EachPlayer);
                        EachPlayer.teleport(defaultspawn);
                    }
                }
                break;
            case 1:
                for (Player EachPlayer : BlueTeam) {
                    AlivePlayers.add(EachPlayer);
                    Bukkit.getLogger().info("player found with name of:" + EachPlayer.getName().toString());
                    players++;
                    boolean TeamEqual = true;
                    if (CurrentOperators.Check(EachPlayer, 1)) {
                        Loadouts.GiveAbilityItem(1, EachPlayer);
                    }
                    if (CurrentOperators.Check(EachPlayer, 2)) {
                        Loadouts.GiveAbilityItem(2, EachPlayer);
                    }
                    if (CurrentOperators.Check(EachPlayer, 3)) {
                        Loadouts.GiveAbilityItem(3, EachPlayer);
                    }
                    //CHECK FOR UNEVEN TEAMS
                    Location defaultspawndef = new Location(world, 723, 15, -662, 0f, 1.2f);

                    Bukkit.getLogger().info("blue team: " + EachPlayer);
                    EachPlayer.teleport(defaultspawndef);
                }
                break;
        }
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.broadcastMessage(ChatColor.RED + "ROUND "  + GameRounds.getCurrentRound());
        InitGame();

    }
    public static void ResendTeleportRed() {
        if(joinedPlayers >= GameChat.GetAllPlayers().size() && PickedOperators >= GameChat.GetAllPlayers().size()) {
            Bukkit.getLogger().info("players: " + GameChat.GetAllPlayers().size());
            TeleportTeams(2);
        }
    }
    public static void ResendTeleportBlue() {
        if(joinedPlayers >= GameChat.GetAllPlayers().size() && PickedOperators >= GameChat.GetAllPlayers().size()) {
            Bukkit.getLogger().info("players: " + GameChat.GetAllPlayers().size());
            TeleportTeams(1);
        }
    }
    public static boolean HasGivenBooks = false;
    @Deprecated
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
    public static long Seconds = 100,MaxSeconds = 100;
    public static void StartTimer(){
    PlayersCanMove = true;
    Timer timer = new Timer(Seconds,MaxSeconds);
    timer.startTimer();
}
    public static boolean GameStarted = false;
    public static void CreatePlayerClass(Player p , String Name, String Class,int ID,int Team){
        if(!PlayerClasses.containsKey(Name)){
            PlayerClasses.put(Name,new PlayerClass(p,Name,Class,ID,Team));
            Bukkit.getLogger().info(PlayerClasses.toString());
            Bukkit.getLogger().info("CreatedPlayerClass");
        }
    }
}

