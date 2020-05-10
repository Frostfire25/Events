package co.antiqu.vibeevents.Events;

import co.antiqu.vibeevents.VibeEvents;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public abstract class Event implements Listener {

    public abstract Player getWinner();
    public abstract String getName();
    public abstract void setOn();
    public abstract void setOff();
    public abstract String getDescription();

}
