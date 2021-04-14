package me.R6SMC.plugin.GameLogic;

import me.R6SMC.plugin.Loadouts.Loadouts;
import me.R6SMC.plugin.Sounds.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {
    public static float Seconds,MaxSeconds;
    private static boolean RedBar = false;
    public Timer(float seconds,float maxseconds){
        Initialize(seconds,maxseconds);
    }
    public static void Initialize(float seconds,float maxseconds){
        MaxSeconds = maxseconds;
        Seconds = seconds;
        Bukkit.getLogger().info(ChatColor.RED + "FULL TIME: " + MaxSeconds);
    }
    public void startTimer() {
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
        this.runTaskTimer(GameLogic.mainThread, 0,20L);

    }
    @Override
    public void run() {
            if (GameLogic.GameStarted) {
                if(Seconds <= 0){
                    Bukkit.broadcastMessage(ChatColor.RED + "THE GAME HAS ENDED");

                    GameLogic.GameStarted = false;
                    GameLogic.EndGame();
                    cancel();
                }
                if (GameLogic.GameTime >= 0.01) {
                    GameLogic.GameTime -= 1/MaxSeconds;
                    Seconds --;
                    RedBar = !RedBar;
                    if(Seconds <= 15){
                        //PLAY ENDING GAME SOUND
                        if(RedBar){
                            GameLogic.B.setTitle(ChatColor.RED + GameLogic.B.getTitle());
                        }
                        if(!RedBar){
                            GameLogic.B.setTitle(ChatColor.WHITE + GameLogic.B.getTitle());
                        }
                    }

                    GameLogic.B.setProgress(GameLogic.GameTime);
                    GameLogic.B.setTitle(" : " + Seconds + " : ");
                } else {
                    Bukkit.broadcastMessage(ChatColor.RED + "THE GAME HAS ENDED");
                    GameLogic.GameStarted = false;
                    GameLogic.EndGame();
                    cancel();

                }

            }
        }
//HELLO?








}
