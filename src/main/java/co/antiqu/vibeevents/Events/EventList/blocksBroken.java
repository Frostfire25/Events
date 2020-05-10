package co.antiqu.vibeevents.Events.EventList;

import co.antiqu.vibeevents.Events.Event;
import co.antiqu.vibeevents.Events.EventType;
import co.antiqu.vibeevents.Util.Color;
import co.antiqu.vibeevents.VibeEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;

public class blocksBroken extends Event {

    private final String name = "Blocks Broken Event";
    private final EventType eventType = EventType.BLOCKS_BROKEN;
    private HashMap<Player,Integer> blocks_broken_map;
    private boolean on;
    private final static VibeEvents inst = VibeEvents.getInstance();

    public blocksBroken() {
        Bukkit.getPluginManager().registerEvents(this, inst);
        blocks_broken_map = new HashMap<>();
    }

    public EventType getEventType() {
        return eventType;
    }

    @EventHandler
    public void onCaneBroken(BlockBreakEvent evt) {
        if(!on) {
            return;
        }
        int broken = 0;
        if(blocks_broken_map.containsKey(evt.getPlayer())) {
            broken = blocks_broken_map.get(evt.getPlayer()) + 1;
        }
        blocks_broken_map.put(evt.getPlayer(),broken);
    }

    public Player getWinner() {
        Player player = null;
        int broken_top = 0;
        for(Player n : blocks_broken_map.keySet()) {
            if(n == null || !n.isOnline()) {
                continue;
            }
            if(blocks_broken_map.get(n) > broken_top) {
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
        return Color.t("Break the most Blocks within the next 5 minutes to win!");
    }


}
