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
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class cactusPlaced extends Event {


    private final String name = "Cactus Placed Event";
    private final EventType eventType = EventType.CACTUS_PLACED;
    private HashMap<Player,Integer> cactus_placed_map;
    private boolean on;
    private final static VibeEvents inst = VibeEvents.getInstance();

    public cactusPlaced() {
        Bukkit.getPluginManager().registerEvents(this, inst);
        cactus_placed_map = new HashMap<>();
    }

    public EventType getEventType() {
        return eventType;
    }

    @EventHandler
    public void onCaneBroken(BlockPlaceEvent evt) {
        if(!on) {
            return;
        }
        if(evt.getBlockPlaced().getType() != Material.CACTUS) {
            return;
        }
        int placed = 0;
        if(cactus_placed_map.containsKey(evt.getPlayer())) {
            placed = cactus_placed_map.get(evt.getPlayer()) + 1;
        }
        cactus_placed_map.put(evt.getPlayer(),placed);
    }

    public Player getWinner() {
        Player player = null;
        int cactus_placed_top = 0;
        for(Player n : cactus_placed_map.keySet()) {
            if(n == null || !n.isOnline()) {
                continue;
            }
            if(cactus_placed_map.get(n) > cactus_placed_top) {
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
        return Color.t("Place the most Cactus within the next 5 minutes to win!");
    }


}
