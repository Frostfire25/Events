package co.antiqu.vibeevents.Events;

import co.antiqu.vibeevents.Events.EventList.*;
import co.antiqu.vibeevents.VibeEvents;

import java.util.ArrayList;
import java.util.List;

public class EventManagement {

    private VibeEvents inst;
    private static List<EventType> eventList;

    public EventManagement(VibeEvents inst) {
        this.inst = inst;
        setUp();
    }

    public static Event getRandomEvent() {
        return getEventFromEnum(eventList.get((int) (Math.random() * eventList.size())));
    }

    private static Event getEventFromEnum(EventType evt) {
        switch (evt) {
            case KILL: return new killEvent();
            case SUAGR_CANE_BROKEN: return new caneBroken();
            case BLOCKS_RAN: return new blocksMoved();
            case CACTUS_PLACED: return new cactusPlaced();
            case BLOCKS_PLACED: return new blocksPlaced();
            case BLOCKS_BROKEN: return new blocksBroken();
            case MOBS_KILLED: return new mobsKilled();
        }
        return null;
    }

    private void setUp() {
        this.eventList = new ArrayList<>();
        eventList.add(EventType.KILL);
        eventList.add(EventType.SUAGR_CANE_BROKEN);
        eventList.add(EventType.BLOCKS_RAN);
        eventList.add(EventType.CACTUS_PLACED);
        eventList.add(EventType.BLOCKS_PLACED);
        eventList.add(EventType.BLOCKS_BROKEN);
        eventList.add(EventType.MOBS_KILLED);
    }

}
