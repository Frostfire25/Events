package co.antiqu.vibeevents.Events.EventList;

import co.antiqu.vibeevents.Events.Event;
import co.antiqu.vibeevents.Events.EventType;
import co.antiqu.vibeevents.Util.Color;
import co.antiqu.vibeevents.VibeEvents;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;

public class blocksPlaced extends Event {


    private final String name = "Blocks Placed Event";
    private final EventType eventType = EventType.BLOCKS_PLACED;
    private HashMap<Player,Integer> blocks_placed_map;
    private boolean on;
    private final static VibeEvents inst = VibeEvents.getInstance();

    public blocksPlaced() {
        Bukkit.getPluginManager().registerEvents(this, inst);
        blocks_placed_map = new HashMap<>();
    }

    public EventType getEventType() {
        return eventType;
    }

    @EventHandler
    public void onCaneBroken(BlockPlaceEvent evt) {
        if(!on) {
            return;
        }
        int placed = 0;
        if(blocks_placed_map.containsKey(evt.getPlayer())) {
            placed = blocks_placed_map.get(evt.getPlayer()) + 1;
        }
        blocks_placed_map.put(evt.getPlayer(),placed);
    }

    public Player getWinner() {
        Player player = null;
        int placed_top = 0;
        for(Player n : blocks_placed_map.keySet()) {
            if(n == null || !n.isOnline()) {
                continue;
            }
            if(blocks_placed_map.get(n) > placed_top) {
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
        return Color.t("Place the most Blocks within the next 5 minutes to win!");
    }


}
