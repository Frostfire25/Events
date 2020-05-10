package co.antiqu.vibeevents.Events.EventList;

import co.antiqu.vibeevents.Events.Event;
import co.antiqu.vibeevents.Events.EventType;
import co.antiqu.vibeevents.Util.Color;
import co.antiqu.vibeevents.VibeEvents;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class blocksMoved extends Event {

    private final String name = "Blocks Ran Event";
    private final EventType eventType = EventType.BLOCKS_RAN;
    private HashMap<Player,Integer> blocks_ran_map;
    private boolean on;
    private final static VibeEvents inst = VibeEvents.getInstance();

    public blocksMoved() {
        Bukkit.getPluginManager().registerEvents(this, inst);
        blocks_ran_map = new HashMap<>();
    }

    public EventType getEventType() {
        return eventType;
    }

    @EventHandler
    public void onCaneBroken(PlayerMoveEvent evt) {
        if(!on) {
            return;
        }
        int moved = 0;
        if(blocks_ran_map.containsKey(evt.getPlayer())) {
            moved = blocks_ran_map.get(evt.getPlayer()) + 1;
        } else {
            blocks_ran_map.put(evt.getPlayer(), moved+1);
        }
    }

    public Player getWinner() {/*
        Player player = null;
        int blocks_ran_top = 0;
        for(Player n : blocks_ran_map.keySet()) {
            if(n == null || !n.isOnline()) {
                continue;
            }
            if(blocks_ran_map.get(n) > blocks_ran_top) {
                player = n;
            }
        }
        return player;*/
        return blocks_ran_map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
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
        return Color.t("Run the most Blocks within the next 5 minutes to win!");
    }

}
