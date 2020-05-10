package co.antiqu.vibeevents.Util;

import co.antiqu.vibeevents.Events.Event;
import co.antiqu.vibeevents.Events.EventManagement;
import co.antiqu.vibeevents.VibeEvents;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerManagement {

    private static VibeEvents inst;

    private static Event event;

    private static int event_timer;
    private static int timer;

    public TimerManagement(VibeEvents inst) {
        this.inst = inst;
        setUp();
    }

    public static void reset() {
        event = null;
        setUp();
        timer = 1;
    }

    private static void setUp() {
        timer = 2100;//2100
        event_timer = 300;//300
        new BukkitRunnable() {
            @Override
            public void run() {
                if(--timer == 0) {
                    //Event Starts
                    event = EventManagement.getRandomEvent();
                    event.setOn();
                    sendStartMessage();
                    timer = 2100;
                }

                if(event != null) {
                    if(--event_timer == 0) {
                        //Event Over
                        event.setOff();
                        Player winner = event.getWinner();
                        sendWinnerMesage(winner);
                        giveAway(winner);
                        event_timer = 300;
                        event = null;
                    }
                    if(event_timer == 150) {
                        sendHalfMessage();
                    }
                }
            }
        }.runTaskTimer(inst,0, 20);
    }

    private static void sendStartMessage() {
        for(Player n : Bukkit.getOnlinePlayers()) {
            n.sendMessage(Color.t("" +
                    "\n&f" +
                    "\n&f&e&lVIBE&6&lPvP &8» &fAn Event Has Started!" +
                    "\n" +
                    "&eEvent Type: &f"+event.getName()+"&f." +
                    "\n" +
                    "&eDescription: &f&o"+event.getDescription()+"&f." +
                    "\n&f" +
                    "\n&f").split("\n"));
        }
    }

    private static void sendHalfMessage() {
        for(Player n : Bukkit.getOnlinePlayers()) {
            n.sendMessage(Color.t("" +
                    "\n&f" +
                    "\n&f&e&lVIBE&6&lPvP &8» &fAn Event is Half-Way Over!" +
                    "\n" +
                    "&eEvent Type: &f"+event.getName()+"&f." +
                    "\n" +
                    "&eTime Left: &f&o2 1/2 Minutes&f." +
                    "\n&f" +
                    "\n&f").split("\n"));
        }
    }

    private static void sendWinnerMesage(Player winner) {
        String winner_name = "";
        if(winner == null || !winner.isOnline()) {
            winner_name = "none";
        } else {
            winner_name = winner.getName();
        }
        for(Player n : Bukkit.getOnlinePlayers()) {
            n.sendMessage(Color.t("\n&f" +
                    "\n&f" +
                    "&e&lVIBE&6&lPvP &8» &fThe Event has Finished!" +
                    "\n" +
                    "&eEvent Type: &f"+event.getName()+"&f." +
                    "\n" +
                    "&eWinner: &f&o"+winner_name+"&f." +
                    "\n&f" +
                    "\n&f").split("\n"));
        }
    }

    private static void giveAway(Player player) {
        if(player == null || !player.isOnline()) {
            return;
        }
        String[] rewards = {"give %player% 383:50 8","eco give %player% 75000","eco give %player% 150000","cc give v spawner 1 %player%"};
        String reward = rewards[(int) (Math.random() * rewards.length)].replaceAll("%player%", player.getName());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), reward);
    }

}
