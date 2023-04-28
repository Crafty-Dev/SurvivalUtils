package de.crafty.survivalutils.command;

import de.crafty.survivalutils.SurvivalUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_ping implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player player) || args.length > 0)
            return false;

        if("ping".equalsIgnoreCase(cmd.getName())){
            sender.sendMessage(SurvivalUtils.PREFIX + "Ping: \u00a76" + player.getPing() + "\u00a77ms");
            return true;
        }
        return false;
    }

}
