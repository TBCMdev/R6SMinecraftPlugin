package me.R6SMC.plugin.GameLogic;

import me.R6SMC.plugin.Loadouts.Loadouts;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {
    public static float TimerDelay, TimerPeriod;
    public static float FullTime;
    public static float TimeElapsed,TimeRemaining;
    public static float TimeBetweenDecrease;
    public Timer(long TD,long TP){
        Initialize(TD,TP);
    }
    public static void Initialize(long TD,long TP){
        TimerDelay = (float)TD;
        TimerPeriod = (float)TP;
        FullTime = ((TimerDelay / 20) + (TimerPeriod / 20)) * 100;
        TimeBetweenDecrease = ((TimerDelay / 20) + (TimerPeriod / 20));
        TimeElapsed = 0;
        Bukkit.getLogger().info(ChatColor.RED + "FULL TIME: " + FullTime + " TIME BETWEEN DECREASE: " + TimeBetweenDecrease);
    }
    public void startTimer() {
        Initialize((long)TimerDelay,(long)TimerPeriod);
        GameLogic.GameTime = 1f;
        GameLogic.GameStarted = true;
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(!GameLogic.B.getPlayers().contains(p)) {
                GameLogic.B.addPlayer(p);
                Bukkit.getLogger().info("player added");
            }else {
                GameLogic.B.removePlayer(p);
                Bukkit.getLogger().info("player removed and added");
                GameLogic.B.addPlayer(p);
            }
        }
        Loadouts.GiveLoadouts(GameLogic.PlayerClasses);
        this.runTaskTimer(GameLogic.mainThread, (long)TimerDelay,(long)TimerPeriod);

    }
    public void run() {
            if (GameLogic.GameStarted) {
                GameLogic.GameTime -= 0.01f;
                if (GameLogic.GameTime >= 0.01) {
                    TimeElapsed += TimeBetweenDecrease;
                    TimeRemaining = FullTime - TimeElapsed;
                    GameLogic.B.setProgress(GameLogic.GameTime);
                    GameLogic.B.setTitle(" : " + TimeRemaining + " : ");
                } else {
                    Bukkit.broadcastMessage(ChatColor.RED + "THE GAME HAS ENDED");

                    GameLogic.GameStarted = false;
                    GameLogic.EndGame();
                    cancel();
                    return;

                }

            }
        }
//HELLO?








}
