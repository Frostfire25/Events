package co.antiqu.vibeevents;

import co.antiqu.vibeevents.Commands.StartEvent;
import co.antiqu.vibeevents.Events.EventManagement;
import co.antiqu.vibeevents.Util.TimerManagement;
import org.bukkit.plugin.java.JavaPlugin;

public final class VibeEvents extends JavaPlugin {

    private static VibeEvents instance;
    private EventManagement eventManagement;
    private TimerManagement timerManagement;

    @Override
    public void onEnable() {
        this.instance = this;
        register();
    }

    private void register() {
        config();
        commands();
        listeners();
        objects();
    }

    private void commands() {
        getCommand("startevent").setExecutor(new StartEvent());
    }

    private void listeners() {

    }

    private void objects() {
        eventManagement = new EventManagement(this);
        timerManagement = new TimerManagement(this);
    }

    private void config() {
        getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    }

    public static VibeEvents getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
    }
}
