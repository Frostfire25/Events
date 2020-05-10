package co.antiqu.vibeevents.Events;

public enum EventType {

    KILL("KILL"),
    BLOCKS_BROKEN("BLOCKS_BROKEN"),
    BLOCKS_PLACED("BLOCKS_PLACED"),
    SUAGR_CANE_BROKEN("SUAGR_CANE_BROKEN"),
    CACTUS_PLACED("CACTUS_PLACED"),
    BLOCKS_RAN("BLOCKS_RAN"),
    MOBS_KILLED("MOBS_KILLED");

    private String s;

    EventType(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}
