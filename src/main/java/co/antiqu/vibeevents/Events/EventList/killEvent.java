package co.antiqu.vibeevents.Events.EventList;

import co.antiqu.vibeevents.Events.Event;
import co.antiqu.vibeevents.Events.EventType;
import co.antiqu.vibeevents.Util.Color;
import co.antiqu.vibeevents.VibeEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.UUID;

public class killEvent extends Event {

    private final String name = "Players Killed Event";
    private final EventType eventType = EventType.KILL;
    private HashMap<Player,Integer> kills_map;
    private boolean on;
    private final static VibeEvents inst = VibeEvents.getInstance();

    public killEvent() {
        Bukkit.getPluginManager().registerEvents(this, inst);
        kills_map = new HashMap<>();
    }

    public EventType getEventType() {
        return eventType;
    }

    @EventHandler
    public void onKill(EntityDamageByEntityEvent evt) {
        if(!on) {
            return;
        }
        if(evt.getEntity().getType() != EntityType.PLAYER || evt.getDamager().getType() != EntityType.PLAYER) {
            return;
        }
        Player killer = (Player) evt.getDamager();
        Player killed = (Player) evt.getEntity();
        if(!killed.isDead()){
            return;
        }
        int kills = 0;
        if(kills_map.containsKey(killer)) {
            kills = kills_map.get(killer) + 1;
        }
        kills_map.put(killer,kills);
    }

    public Player getWinner() {
        Player player = null;
        int kills = 0;
        for(Player n : kills_map.keySet()) {
            if(n == null || !n.isOnline()) {
                continue;
            }
            if(kills_map.get(n) > kills) {
                player = n;
            }
        }
        return player;
    }

    @Override
    public String getDescription() {
        return Color.t("Kill the most Players within the next 5 minutes to win!");
    }

    public boolean isOn() {
        return on;
    }

    public void setOn() {
        this.on = true;
    }

    public void setOff() {
        this.on = false;
    }

    public String getName() {
        return name;
    }
}
