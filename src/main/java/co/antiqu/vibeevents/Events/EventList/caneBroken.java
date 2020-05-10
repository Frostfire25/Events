package co.antiqu.vibeevents.Events.EventList;

import co.antiqu.vibeevents.Events.Event;
import co.antiqu.vibeevents.Events.EventType;
import co.antiqu.vibeevents.Util.Color;
import co.antiqu.vibeevents.VibeEvents;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;

public class caneBroken extends Event {

    private final String name = "Cane Broken Event";
    private final EventType eventType = EventType.SUAGR_CANE_BROKEN;
    private HashMap<Player,Integer> cane_map;
    private boolean on;
    private final static VibeEvents inst = VibeEvents.getInstance();

    public caneBroken() {
        Bukkit.getPluginManager().registerEvents(this, inst);
        cane_map = new HashMap<>();
    }

    public EventType getEventType() {
        return eventType;
    }

    @EventHandler
    public void onCaneBroken(BlockBreakEvent evt) {
        if(!on) {
            return;
        }
        if(evt.getBlock().getType() != Material.SUGAR_CANE_BLOCK) {
            return;
        }
        int cane = 0;
        if(cane_map.containsKey(evt.getPlayer())) {
            cane = cane_map.get(evt.getPlayer()) + 1;
        }
        cane_map.put(evt.getPlayer(),cane);
    }

    public Player getWinner() {
        Player player = null;
        int cane_top = 0;
        for(Player n : cane_map.keySet()) {
            if(n == null || !n.isOnline()) {
                continue;
            }
            if(cane_map.get(n) > cane_top) {
                player = n;
            }
        }
        return player;
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

    @Override
    public String getDescription() {
        return Color.t("Break the most Sugar Cane within the next 5 minutes to win!");
    }
}
