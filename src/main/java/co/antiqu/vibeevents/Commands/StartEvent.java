package co.antiqu.vibeevents.Commands;

import co.antiqu.vibeevents.Util.TimerManagement;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartEvent implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(!sender.hasPermission("vibeevents.*")) {
                return false;
            }
        }
        TimerManagement.reset();
        return false;
    }
}
